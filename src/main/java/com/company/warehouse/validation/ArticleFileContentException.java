package com.company.warehouse.validation;

public class ArticleFileContentException extends RuntimeException {
    public ArticleFileContentException() {
        super("There is something wrong with the product file format.");
    }
}