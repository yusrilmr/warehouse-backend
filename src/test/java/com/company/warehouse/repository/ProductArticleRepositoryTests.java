package com.company.warehouse.repository;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.Product;
import com.company.warehouse.domain.ProductArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductArticleRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductArticleRepository productArticleRepository;

    @Test
    public void insertProductArticle_ProductArticleDataIsComplete_ProductArticleIsSaved() {
        Article article = new Article("TB28179", "table leg", 5L);
        Product product = new Product("Picnic Table", BigDecimal.valueOf(199.99));
        ProductArticle productArticle = new ProductArticle(product, article, 4L);
        productArticleRepository.save(productArticle);
        assertThat(productArticle.getId()).isNotNull();
    }

    @Test
    public void deleteProductArticles_ProductArticleIsComplete_AllProductArticlesDeleted() {
        testEntityManager.persistAndFlush(new Article("TB22222", "table leg", 5L));
        testEntityManager.persistAndFlush(new Article("TB22223", "table top", 5L));
        productArticleRepository.deleteAll();
        assertThat(productArticleRepository.findAll()).isEmpty();
    }
}
