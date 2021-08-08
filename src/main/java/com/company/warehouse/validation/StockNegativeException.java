package com.company.warehouse.validation;

public class StockNegativeException extends RuntimeException {
    public StockNegativeException() {
        super("The stock value cannot be negative after you sell a product.");
    }
}