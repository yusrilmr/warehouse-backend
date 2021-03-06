package com.company.warehouse.controller;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.file.ArticleFile;
import com.company.warehouse.domain.file.ArticleFileDetail;
import com.company.warehouse.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllArticles_IsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(get("/articles")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void getArticlesByIds_IdsIsListOfNumber_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(get("/articles-by-ids?ids=1,2,3")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void getArticleById_IdIsANumber_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(get("/articles/1")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void insertArticle_ContentIsValid_Return200Ok() throws Exception {
        Article article = new Article();
        article.setIdentification("TBL222022");
        article.setName("Table");
        article.setStock(20L);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(article);

        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(post("/articles")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

    @Test
    public void uploadArticleFile_ContentIsValid_Return200Ok() throws Exception {
        ArticleFileDetail articleFileDetail = new ArticleFileDetail();
        articleFileDetail.setArt_id("1");
        articleFileDetail.setName("Article 1");
        articleFileDetail.setStock("22");

        ArticleFile articleFile = new ArticleFile();
        articleFile.setInventory(Arrays.asList(articleFileDetail));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(articleFile);

        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(post("/articles/upload")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

    @Test
    public void updateArticle_ContentIsValid_Return200Ok() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Article("TBL22212", "Table", 20L));

        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(put("/articles/1")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());

    }

    @Test
    public void deleteArticle_ContentIsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(delete("/articles/1")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
