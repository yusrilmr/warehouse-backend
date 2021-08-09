package com.company.warehouse.controller;

import com.company.warehouse.service.ProductArticleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductArticleController {
    private final ProductArticleService productArticleService;

    public ProductArticleController(ProductArticleService productArticleService){
        this.productArticleService = productArticleService;
    }

    /**
     * Delete a ProductArticle based on id.
     * @param id ProductArticle id.
     */
    @DeleteMapping("/product-articles/{id}")
    void deleteProductArticleById(@PathVariable Long id) {
        productArticleService.deleteProductArticleById(id);
    }
}
