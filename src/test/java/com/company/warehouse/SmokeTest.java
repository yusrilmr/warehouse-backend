package com.company.warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.warehouse.controller.ArticleController;
import com.company.warehouse.controller.ProductArticleController;
import com.company.warehouse.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {
    @Autowired
    private ArticleController articleController;
    @Autowired
    private ProductController productController;
    @Autowired
    private ProductArticleController productArticleController;

    @Test
    public void contextLoads() {
        assertThat(articleController).isNotNull();
        assertThat(productController).isNotNull();
        assertThat(productArticleController).isNotNull();
    }

}
