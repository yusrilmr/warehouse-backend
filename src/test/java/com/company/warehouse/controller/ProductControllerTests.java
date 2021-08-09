package com.company.warehouse.controller;

import com.company.warehouse.domain.Article;
import com.company.warehouse.domain.Product;
import com.company.warehouse.domain.file.ProductFile;
import com.company.warehouse.domain.file.ProductFileProduct;
import com.company.warehouse.domain.file.ProductFileProductDetail;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllProducts_IsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(get("/products")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void getProduct_IsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(get("/products/{id}",4)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void insertProduct_IsValid_Return200Ok() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Product("Diner Table", BigDecimal.valueOf(199.99)));

        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(post("/products")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

    @Test
    public void uploadArticleFile_IsValid_Return200Ok() throws Exception {
        List<ProductFileProductDetail> productFileProductDetails = new ArrayList<>();
        productFileProductDetails.add(new ProductFileProductDetail("TB28179", "2"));
        List<ProductFileProduct> productFileProducts = new ArrayList<>();
        productFileProducts.add(new ProductFileProduct("Diner Table", productFileProductDetails));
        ProductFile productFile = new ProductFile(productFileProducts);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(productFile);

        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(post("/products")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateProduct_IsValid_Return200Ok() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new Product("Diner Table", BigDecimal.valueOf(199.99)));

        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(put("/products/1")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                        .andExpect(status().isOk());
    }

    @Test
    public void deleteProduct_IsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(delete("/products/{id}",4)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void sellProduct_IsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(put("/products/sell/1/1")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void getAllProductsAndQuantity_IsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(get("/product-quantities/")
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void getProductDetail_IsValid_Return200Ok() throws Exception {
        String token = AuthenticationService.createToken("admin");
        this.mockMvc.perform(get("/product-details/{id}", 1)
                        .header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
