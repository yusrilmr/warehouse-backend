package com.company.warehouse.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ArticleFileContentAdvice {

    @ResponseBody
    @ExceptionHandler(ArticleFileContentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String articleFileFormatHandler(ArticleFileContentException ex) {
        return ex.getMessage();
    }
}