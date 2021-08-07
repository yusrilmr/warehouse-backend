package com.company.warehouse.domain.file;

public class ArticleFileDetail {
    private String art_id;
    private String name;
    private String stock;

    public ArticleFileDetail(){}
    public ArticleFileDetail(String art_id, String name, String stock) {
        this.art_id = art_id;
        this.name = name;
        this.stock = stock;
    }

    public String getArt_id() {
        return art_id;
    }

    public void setArt_id(String art_id) {
        this.art_id = art_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
