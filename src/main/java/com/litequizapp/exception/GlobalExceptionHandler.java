package com.litequizapp.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ElementNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(
      ElementNotFoundException ex) {

    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());

    ApiError err = new ApiError(
        LocalDateTime.now(),
        HttpStatus.NOT_FOUND,
        "The record doesn't exist",
        details);

    return ResponseEntityBuilder.build(err);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(
      Exception ex) {

    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());

    ApiError err = new ApiError(
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST,
        "Constraint Violations",
        details);

    return ResponseEntityBuilder.build(err);
  }

  @Override
  @NonNull
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatus status,
      @NonNull WebRequest request) {

    List<String> details = new ArrayList<>();
    details = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
        .collect(Collectors.toList());

    ApiError err = new ApiError(
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST,
        "Validation Errors",
        details);

    return ResponseEntityBuilder.build(err);
  }

}
