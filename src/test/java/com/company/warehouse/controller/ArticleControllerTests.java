package com.company.warehouse.controller;

import com.company.warehouse.domain.Article;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTests {
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testAuthentication() throws Exception {
//        // Testing authentication with correct credentials
//        this.mockMvc.perform(post("/login")
//                    .content("{\"username\":\"admin\", \"password\":\"admin\"}"))
//                    .andDo(print()).andExpect(status().isOk());
//        // Testing authentication with wrong credentials
//        this.mockMvc.perform(post("/login")
//                    .content("{\"username\":\"admin\", \"password\":\"wrongpwd\"}"))
//                    .andDo(print()).andExpect(status().is4xxClientError());
//    }

    @Test
    public void getAllArticles() {
        Article article1 = new Article("TB22222", "table leg", 5L);
        Article article2 = new Article("TB22222", "table top", 4L);
        Article article3 = new Article("TB22222", "screw", 20L);


    }
}
