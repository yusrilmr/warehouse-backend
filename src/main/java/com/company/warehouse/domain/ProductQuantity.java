package com.company.warehouse.domain;

import java.math.BigDecimal;

public class ProductQuantity {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Long quantity;
    public ProductQuantity(){}
    public ProductQuantity(Long productId, String productName, BigDecimal productPrice, Long quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
