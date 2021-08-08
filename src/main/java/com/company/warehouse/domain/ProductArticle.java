package com.company.warehouse.domain;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProductArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name="total_article")
    @JsonProperty("totalArticle")
    private Long total_article;

    @Column(name="created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name="last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    public ProductArticle(){}
    public ProductArticle(Product product, Article article, Long total_article) {
        super();
        this.product = product;
        this.article = article;
        this.total_article = total_article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("productId")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("articleId")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @JsonProperty("articleId")
    public void setArticle(Long id) {
        this.article = new Article(id);
    }

    public Long getTotalArticle() {
        return total_article;
    }

    public void setTotalArticle(Long total_article) {
        this.total_article = total_article;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
