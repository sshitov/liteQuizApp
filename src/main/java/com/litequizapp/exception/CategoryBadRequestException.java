package com.litequizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The category name can't be empty")
public class CategoryBadRequestException extends RuntimeException{

}
