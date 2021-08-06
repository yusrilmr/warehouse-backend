package com.company.warehouse;

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
    public void insertArticle_ArticleDataIsComplete_ArticleIsSaved()
    {
        Article article = new Article("TB22222", "table leg", 5);
        testEntityManager.persistAndFlush(article);
        assertThat(article.getId()).isNotZero();
    }

    @Test
    public void findByIdentification_IdentificationIsNegative_ReturnNull() {

    }

    @Test
    public void deleteCars() {
        testEntityManager.persistAndFlush(new Article("TB22222", "table leg", 5));
        testEntityManager.persistAndFlush(new Article("TB22223", "table top", 5));
        articleRepository.deleteAll();
        assertThat(articleRepository.findAll()).isEmpty();
    }
}
