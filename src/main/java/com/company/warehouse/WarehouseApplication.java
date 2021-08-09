package com.company.warehouse;

import com.company.warehouse.domain.*;
import com.company.warehouse.repository.ArticleRepository;
import com.company.warehouse.repository.ProductArticleRepository;
import com.company.warehouse.repository.ProductRepository;
import com.company.warehouse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class WarehouseApplication {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseApplication.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductArticleRepository productArticleRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
        logger.info("Backend is started");
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            // Add article objects and save these to db
            Article article1 = new Article("TB28179", "table leg", 5L);
            Article article2 = new Article("TB28329", "table top", 7L);
            Article article3 = new Article("SC28329", "screw", 20L);
            Article article4 = new Article("WD28422", "Oak", 30L);
            articleRepository.saveAll(Arrays.asList(article1, article2, article3, article4));

            // Add product object and save these to db.
            Product product1 = new Product("Picnic Table", BigDecimal.valueOf(199.99));
            Product product2 = new Product("Picnic Chair", BigDecimal.valueOf(99.99));
            Product product3 = new Product("Picnic Tent", BigDecimal.valueOf(499.99));
            Product product4 = new Product("Outdoor Table", BigDecimal.valueOf(299.99));
            productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));

            // Link product and article
            ProductArticle productArticle1 = new ProductArticle(product1, article1, 4L);
            ProductArticle productArticle2 = new ProductArticle(product1, article2, 1L);
            ProductArticle productArticle3 = new ProductArticle(product1, article3, 4L);
            ProductArticle productArticle4 = new ProductArticle(product4, article4, 10L);
            productArticleRepository.saveAll(Arrays.asList(
                    productArticle1, productArticle2, productArticle3, productArticle4));

            // Add user
            userRepository.save(new User("user",
                    "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER"));
            userRepository.save(new User("admin",
                    "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN"));
        };
    }

}
