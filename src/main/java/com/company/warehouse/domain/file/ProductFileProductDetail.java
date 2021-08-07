package com.company.warehouse.domain.file;

public class ProductFileProductDetail {
    private String art_id;
    private String amount_of;

    public ProductFileProductDetail(){}
    public ProductFileProductDetail(String art_id, String amount_of) {
        this.art_id = art_id;
        this.amount_of = amount_of;
    }

    public String getArt_id() {
        return art_id;
    }

    public void setArt_id(String art_id) {
        this.art_id = art_id;
    }

    public String getAmount_of() {
        return amount_of;
    }

    public void setAmount_of(String amount_of) {
        this.amount_of = amount_of;
    }
}
