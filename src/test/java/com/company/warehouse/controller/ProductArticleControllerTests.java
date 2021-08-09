package com.company.warehouse.controller;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.Product;
import com.company.warehouse.domain.ProductArticle;
import com.company.warehouse.repository.ProductArticleRepository;
import com.company.warehouse.service.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductArticleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deleteProductArticleById_IdIsNumber_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(delete("/product-articles/{id}", 3)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
