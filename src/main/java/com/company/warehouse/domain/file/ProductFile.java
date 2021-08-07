package com.company.warehouse.domain.file;

import java.util.List;

public class ProductFile {
    private List<ProductFileProduct> products;

    public ProductFile(){}
    public ProductFile(List<ProductFileProduct> products) {
        this.products = products;
    }

    public List<ProductFileProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ProductFileProduct> products) {
        this.products = products;
    }
}