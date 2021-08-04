package com.company.warehouse.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="external_id", unique = true)
    private String externalId;

    @Column(name="name")
    private String name;

    @Column(name="stock")
    private long stock;

    @Column(name="created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name="last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Set<ProductArticle> productArticles;

    // TODO: implement deletion date
//    @Column(name="deletion_date")
//    private Timestamp deletionDate;

    public Article(){}
    public Article(String externalId, String name, long stock) {
        super();
        this.externalId = externalId;
        this.name = name;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
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
