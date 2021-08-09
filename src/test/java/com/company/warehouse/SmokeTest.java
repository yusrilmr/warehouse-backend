package com.company.warehouse;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.warehouse.controller.ArticleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {
    @Autowired
    private ArticleController articleController;

    @Test
    public void contextLoads() throws Exception  {
        assertThat(articleController).isNotNull();
    }

}
