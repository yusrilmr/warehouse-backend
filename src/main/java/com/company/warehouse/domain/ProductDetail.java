package com.company.warehouse.domain;

public class ProductDetail {
    private String identification;
    private String name;
    private Long totalArticle;
    private Long stock;

    public ProductDetail(String identification, String name, Long totalArticle, long stock) {
        this.identification = identification;
        this.name = name;
        this.totalArticle = totalArticle;
        this.stock = stock;
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
