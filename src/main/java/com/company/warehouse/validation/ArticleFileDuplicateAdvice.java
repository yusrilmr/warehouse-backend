package com.company.warehouse.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ArticleFileDuplicateAdvice {

    @ResponseBody
    @ExceptionHandler(ArticleFileDuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String articleFileDuplicateHandler(ArticleFileDuplicateException ex) {
        return ex.getMessage();
    }
}