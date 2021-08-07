package com.company.warehouse.validation;

public class ProductFileContentException extends RuntimeException {
    public ProductFileContentException(Long id) {
        super("There is something wrong with the product file format");
    }
}