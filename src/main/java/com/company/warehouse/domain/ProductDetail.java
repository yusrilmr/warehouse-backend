package com.company.warehouse.domain;

public class ProductDetail {
    private String identification;
    private String name;
    private Long totalArticle;

    public ProductDetail(String identification, String name, Long totalArticle) {
        this.identification = identification;
        this.name = name;
        this.totalArticle = totalArticle;
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
}
