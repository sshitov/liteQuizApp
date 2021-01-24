package com.litequizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The record doesn't exist")
public class ElementNotFoundException extends RuntimeException{

}
