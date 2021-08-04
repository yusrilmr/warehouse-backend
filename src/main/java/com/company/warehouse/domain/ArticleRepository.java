package com.company.warehouse.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Article entity repository
 */
public interface ArticleRepository extends CrudRepository<Article, Long> {

    /**
     * Find article by article id
     * @param id
     * @return
     */
    List<Article> findByExternalId(long id);

    /**
     * Find articles by name
     * @param name
     * @return
     */
    List<Article> findByName(String name);
}
