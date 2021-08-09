package com.company.warehouse.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="identification", unique = true)
    private String identification;

    @Column(name="name")
    private String name;

    @Column(name="stock")
    private Long stock;

    @Column(name="created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name="last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Set<ProductArticle> productArticles;

    public Article(){}
    public Article(Long id){
        this.id = id;
    }
    public Article(String identification, String name, Long stock) {
        super();
        this.identification = identification;
        this.name = name;
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public Set<ProductArticle> getProductArticles() {
        return productArticles;
    }

    public void setProductArticles(Set<ProductArticle> productArticles) {
        this.productArticles = productArticles;
    }
}
