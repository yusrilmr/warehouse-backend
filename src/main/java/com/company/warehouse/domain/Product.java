package com.company.warehouse.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name="last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductArticle> productArticles;

    public Product(){}
    public Product(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
