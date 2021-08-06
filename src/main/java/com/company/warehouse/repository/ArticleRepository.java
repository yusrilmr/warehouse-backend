package com.company.warehouse.repository;

import com.company.warehouse.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Article entity repository
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
    /**
     * Find articles based on list of ids
     * @param ids article ids
     * @return list of articles
     */
    List<Article> findByIdIn(List<Long> ids);
}