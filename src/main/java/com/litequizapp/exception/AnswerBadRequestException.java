package com.litequizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The answer name and question id can't be empty")
public class AnswerBadRequestException extends RuntimeException{

}
