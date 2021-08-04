package com.company.warehouse.domain;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class ProductArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="product_id")
    private long productId;

    @Column(name="article_id")
    private long articleId;

    @Column(name="amount")
    private long amount;

    @Column(name="created_date")
    @CreatedDate
    private Timestamp createdDate;

    @Column(name="last_update")
    @UpdateTimestamp
    private Timestamp lastUpdate;

    public ProductArticle(){}
    public ProductArticle(long productId, long articleId, long amount) {
        super();
        this.productId = productId;
        this.articleId = articleId;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
