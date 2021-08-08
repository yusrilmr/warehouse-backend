package com.company.warehouse.domain;

public class ProductDetail {
    private Long productArticleId;
    private Long articleId;
    private String articleIdentification;
    private String articleName;
    private Long totalArticle;
    private Long stock;

    public ProductDetail(Long productArticleId, Long articleId, String articleIdentification, String articleName,
                         Long totalArticle, long stock) {
        this.productArticleId = productArticleId;
        this.articleId = articleId;
        this.articleIdentification = articleIdentification;
        this.articleName = articleName;
        this.totalArticle = totalArticle;
        this.stock = stock;
    }

    public Long getProductArticleId() {
        return productArticleId;
    }

    public void setProductArticleId(Long productArticleId) {
        this.productArticleId = productArticleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleIdentification() {
        return articleIdentification;
    }

    public void setArticleIdentification(String articleIdentification) {
        this.articleIdentification = articleIdentification;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Long getTotalArticle() {
        return totalArticle;
    }

    public void setTotalArticle(Long totalArticle) {
        this.totalArticle = totalArticle;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
