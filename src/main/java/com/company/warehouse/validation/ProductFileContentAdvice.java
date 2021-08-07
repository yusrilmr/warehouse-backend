package com.company.warehouse.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductFileContentAdvice {

    @ResponseBody
    @ExceptionHandler(ProductFileContentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String productFileContentAdvice(ProductFileContentException ex) {
        return ex.getMessage();
    }
}
