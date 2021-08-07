package com.company.warehouse.domain.file;

import java.util.List;

public class ProductFileProduct {
    private String name;
    private List<ProductFileProductDetail> contain_articles;

    public ProductFileProduct(){}
    public ProductFileProduct(String name, List<ProductFileProductDetail> contain_articles) {
        this.name = name;
        this.contain_articles = contain_articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductFileProductDetail> getContain_articles() {
        return contain_articles;
    }

    public void setContain_articles(List<ProductFileProductDetail> contain_articles) {
        this.contain_articles = contain_articles;
    }
}
