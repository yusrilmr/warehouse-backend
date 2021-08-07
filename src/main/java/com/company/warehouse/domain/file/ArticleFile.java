package com.company.warehouse.domain.file;

import java.util.List;

public class ArticleFile {
    private List<ArticleFileDetail> inventory;

    public ArticleFile(){}
    public ArticleFile(List<ArticleFileDetail> inventory) {
        this.inventory = inventory;
    }

    public List<ArticleFileDetail> getInventory() {
        return inventory;
    }

    public void setInventory(List<ArticleFileDetail> inventory) {
        this.inventory = inventory;
    }
}