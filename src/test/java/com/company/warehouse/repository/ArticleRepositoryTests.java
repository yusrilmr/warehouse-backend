package com.company.warehouse.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import com.company.warehouse.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void insertArticle_ArticleDataIsComplete_ArticleIsSaved() {
        Article article = new Article("TB22222", "table leg", 5L);
        articleRepository.save(article);
        assertThat(article.getId()).isNotNull();
    }

    @Test
    public void deleteArticles_ArticleDataIsComplete_AllArticlesDeleted() {
        testEntityManager.persistAndFlush(new Article("TB22222", "table leg", 5L));
        testEntityManager.persistAndFlush(new Article("TB22223", "table top", 5L));
        articleRepository.deleteAll();
        assertThat(articleRepository.findAll()).isEmpty();
    }

    @Test
    public void findByIdIn_ArticleDataIsComplete_ArticleIsFound() {
        Article article1 = testEntityManager.persistAndFlush(
                new Article("TB22222", "table leg", 5L));
        Article article2 = testEntityManager.persistAndFlush(
                new Article("TB22223", "table top", 5L));
        List<Article> articles = articleRepository.findByIdIn(Arrays.asList(article1.getId(), article2.getId()));
        assertEquals(2, articles.size());
    }

    @Test
    public void findByIdentification_ArticleDataIsComplete_ArticleIsFound() {
        testEntityManager.persistAndFlush(new Article("TB22222", "table leg", 5L));
        Article article = articleRepository.findByIdentification("TB22222");
        assertEquals("TB22222", article.getIdentification());
    }
}
