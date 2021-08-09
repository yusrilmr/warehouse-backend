package com.company.warehouse.service;

import com.company.warehouse.repository.ProductArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductArticleService {
    private final ProductArticleRepository productArticleRepository;

    public ProductArticleService(ProductArticleRepository productArticleRepository){
        this.productArticleRepository = productArticleRepository;
    }

    /**
     * Delete a ProductArticle based on id.
     * @param id ProductArticle id.
     */
    public void deleteProductArticleById(Long id) {
        productArticleRepository.deleteById(id);
    }
}
