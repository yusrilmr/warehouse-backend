package com.company.warehouse.web;

import com.company.warehouse.repository.ProductArticleRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductArticleController {
    private final ProductArticleRepository productArticleRepository;

    ProductArticleController(ProductArticleRepository productArticleRepository){
        this.productArticleRepository = productArticleRepository;
    }

    @DeleteMapping("/product-articles/{id}")
    void deleteProduct(@PathVariable Long id) {
        productArticleRepository.deleteById(id);
    }
}
