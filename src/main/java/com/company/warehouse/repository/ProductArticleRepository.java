package com.company.warehouse.repository;

import com.company.warehouse.domain.ProductArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductArticle entity repository
 */
public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> { }
