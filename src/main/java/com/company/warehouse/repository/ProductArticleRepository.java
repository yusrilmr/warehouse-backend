package com.company.warehouse.repository;

import com.company.warehouse.domain.ProductArticle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductArticle entity repository
 */
public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> { }
