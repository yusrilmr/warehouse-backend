package com.company.warehouse.web;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.Product;
import com.company.warehouse.domain.ProductArticle;
import com.company.warehouse.domain.ProductDetail;
import com.company.warehouse.repository.ProductRepository;
import com.company.warehouse.validation.ProductNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@Transactional
class ProductController {
    private final ProductRepository productRepository;

    ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @GetMapping("/products/{id}/detail")
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

    @PostMapping("/products")
    Product insertProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
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

//    @Transactional(value = "chainedTransactionManager")
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
//                else

            });
            productRepository.save(product);
        }
        productRepository.deleteById(id);
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
