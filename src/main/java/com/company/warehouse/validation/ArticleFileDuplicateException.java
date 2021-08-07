package com.company.warehouse.validation;

public class ArticleFileDuplicateException extends RuntimeException {
    public ArticleFileDuplicateException() {
        super("One of the articles exists.");
    }
}