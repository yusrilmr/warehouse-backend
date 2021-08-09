package com.company.warehouse.service;

import com.company.warehouse.domain.*;
import com.company.warehouse.domain.file.ProductFile;
import com.company.warehouse.repository.ArticleRepository;
import com.company.warehouse.repository.ProductRepository;
import com.company.warehouse.validation.ProductNotFoundException;
import com.company.warehouse.validation.StockNegativeException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;

    public ProductService(ProductRepository productRepository, ArticleRepository articleRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
    }

    /**
     * Get all products
     * @return list of products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get a product based on id
     * @param id product id
     * @return a product
     */
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Insert a product
     * @param newProduct the new product
     */
    public void insertProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    /**
     * Upload the content of the article file
     * @param productFile content of the article file
     */
    public void uploadArticleFile(ProductFile productFile) {
        List<Product> products = new ArrayList<>();
        productFile.getProducts().forEach(prodElement -> {
            Product product = new Product(prodElement.getName(), BigDecimal.ZERO);
            List<ProductArticle> productArticles = new ArrayList<>();
            prodElement.getContain_articles().forEach(artElement -> {
                Article article = articleRepository.findByIdentification(artElement.getArt_id());
                if (article != null) {
                    productArticles.add(new ProductArticle(product, article,
                            Long.parseLong(artElement.getAmount_of())));

                }
            });
            product.setProductArticles(new HashSet<>(productArticles));
            products.add(product);
        });
        productRepository.saveAll(products);
    }

    /**
     * Upsert a product
     * @param newProduct the modified product
     * @param id product id
     * @return the newly updated/insert product
     */
    public Product updateProduct(Product newProduct, Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setProductArticles(newProduct.getProductArticles());
                    product.setPrice(newProduct.getPrice());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    /**
     * Delete a product based on id
     * @param id product id
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Sell a/multiple product(s)
     * @param id the product id
     * @param total total product that wanted to be sold
     */
    public void sellProduct(Long id, Long total) {
        Product product = productRepository.getById(id);
        Set<ProductArticle> productArticles = product.getProductArticles();
        if (!CollectionUtils.isEmpty(productArticles)) {
            productArticles.forEach(productArticle -> {
                long currentStock = productArticle.getArticle().getStock();
                long newStock = currentStock - (productArticle.getTotalArticle() * total);
                if (newStock >= 0)
                    productArticle.getArticle().setStock(newStock);
                else
                    throw new StockNegativeException();
            });
            productRepository.save(product);
        }
    }

    /**
     * Get all Products and its available quantity
     * @return list of product and its quantity
     */
    public List<ProductQuantity> getAllProductsAndQuantity() {
        List<ProductQuantity> results = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            Long quantity = Long.MAX_VALUE;
            if (!CollectionUtils.isEmpty(product.getProductArticles())) {
                for (ProductArticle productArticle : product.getProductArticles()) {
                    Long amount = productArticle.getTotalArticle();
                    Long stock = productArticle.getArticle().getStock();
                    if (stock <= 0 || (amount > stock)) {
                        quantity = 0L;
                        break;
                    }
                    else {
                        quantity = (stock / amount) < quantity ? (stock / amount) : quantity;
                    }
                }
            }
            else {
                quantity = 0L;
            }
            results.add(new ProductQuantity(product.getId(), product.getName(), product.getPrice() , quantity));
        });
        return results;
    }

    /**
     * Get product detail of its article components
     * @param id product id
     * @return the list of the product detail
     */
    public List<ProductDetail> getProductDetail(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        List<ProductDetail> results = new ArrayList<>();
        product.getProductArticles().forEach(productArticle -> {
            Article article = productArticle.getArticle();
            if (article != null)
                results.add(new ProductDetail(productArticle.getId(), article.getId(), article.getIdentification(),
                        article.getName(), productArticle.getTotalArticle(), article.getStock()));
        });
        return results;
    }
}
