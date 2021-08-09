package com.company.warehouse.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.company.warehouse.domain.Article;
import com.company.warehouse.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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
        testEntityManager.persistAndFlush(article);
        assertThat(article.getId()).isNotNull();
    }

    @Test
    public void findByIdentification_IdentificationIsNegative_ReturnNull() {

    }

    @Test
    public void deleteCars_ArticleDataIsComplete_AllArticlesDeleted() {
        testEntityManager.persistAndFlush(new Article("TB22222", "table leg", 5L));
        testEntityManager.persistAndFlush(new Article("TB22223", "table top", 5L));
        articleRepository.deleteAll();
        assertThat(articleRepository.findAll()).isEmpty();
    }
}
