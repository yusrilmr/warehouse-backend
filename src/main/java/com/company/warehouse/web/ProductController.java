package com.company.warehouse.web;

import com.company.warehouse.domain.*;
import com.company.warehouse.domain.file.ProductFile;
import com.company.warehouse.repository.ArticleRepository;
import com.company.warehouse.repository.ProductRepository;
import com.company.warehouse.validation.ProductNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Transactional
class ProductController {
    private final ProductRepository productRepository;
    private final ArticleRepository articleRepository;

    ProductController(ProductRepository productRepository, ArticleRepository articleRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/products")
    void insertProduct(@RequestBody Product newProduct) {
        productRepository.save(newProduct);
    }

    @PostMapping("/products/upload")
    void uploadArticleFile(@RequestBody ProductFile productFile) {
        List<Product> products = new ArrayList<>();
        productFile.getProducts().forEach(prodElement -> {
            Product product = new Product(prodElement.getName());
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

    @PutMapping("/products/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @DeleteMapping("/products/sell/{id}")
    void sellProduct(@PathVariable Long id) {
        // TODO: implement chain transaction
        Product product = productRepository.getById(id);
        Set<ProductArticle> productArticles = product.getProductArticles();
        if (!CollectionUtils.isEmpty(productArticles)) {
            productArticles.forEach(productArticle -> {
                long currentStock = productArticle.getArticle().getStock();
                long newStock = currentStock - productArticle.getTotalArticle();
                if (newStock > 0)
                    productArticle.getArticle().setStock(newStock);
            });
            productRepository.save(product);
        }
        productRepository.deleteById(id);
    }

    // tag-start::Custom product objects
    @GetMapping("/product-quantities/")
    List<ProductQuantity> getAllProductsAndQuantity() {
        List<ProductQuantity> results = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            Long quantity = Long.MAX_VALUE;
            if (!CollectionUtils.isEmpty(product.getProductArticles())) {
                for (ProductArticle productArticle : product.getProductArticles()) {
                    Long amount = productArticle.getTotalArticle();
                    Long stock = productArticle.getArticle().getStock();
                    if (amount > stock) {
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
            results.add(new ProductQuantity(product.getId(), product.getName(), quantity));
        });
        return results;
    }

    @GetMapping("/product-details/{id}")
    List<ProductDetail> getProductDetail(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        List<ProductDetail> results = new ArrayList<>();
        product.getProductArticles().forEach(productArticle -> {
            Article article = productArticle.getArticle();
            if (article != null)
                results.add(new ProductDetail(article.getIdentification(), article.getName(),
                        productArticle.getTotalArticle(), article.getStock()));
        });
        return results;
    }
    // tag-end::get-aggregate-root[]
}
