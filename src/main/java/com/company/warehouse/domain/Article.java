package com.company.warehouse.domain;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="external_id", unique = true)
    private long externalId;

    @Column(name="name")
    private String name;

    @Column(name="stock")
    private long stock;

    @Column(name="created_date")
    @CreatedDate
    private Timestamp createdDate;

    @Column(name="last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;

    // TODO: implement deletion date
//    @Column(name="deletion_date")
//    private Timestamp deletionDate;

    public Article(){}
    public Article(long externalId, String name, long stock) {
        super();
        this.externalId = externalId;
        this.name = name;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public long getExternalId() {
        return externalId;
    }

    public void setExternalId(long externalId) {
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
}
