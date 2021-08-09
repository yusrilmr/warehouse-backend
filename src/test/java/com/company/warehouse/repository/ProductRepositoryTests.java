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
public class ProductRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertProduct_ProductDataIsComplete_ProductIsSaved() {
        Product product = new Product("Picnic Table", BigDecimal.valueOf(199.99));
        productRepository.save(product);
        assertThat(product.getId()).isNotNull();
    }

    @Test
    public void deleteProducts_ProductIsComplete_AllProductsDeleted() {
        testEntityManager.persistAndFlush(new Product("Picnic Table", BigDecimal.valueOf(199.99)));
        testEntityManager.persistAndFlush(new Product("Diner Table", BigDecimal.valueOf(299.99)));
        productRepository.deleteAll();
        assertThat(productRepository.findAll()).isEmpty();
    }
}
