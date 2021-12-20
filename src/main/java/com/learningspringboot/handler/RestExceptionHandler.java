package com.learningspringboot.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.learningspringboot.exception.BadRequestException;
import com.learningspringboot.exception.BadRequestExceptionDetails;
import com.learningspringboot.exception.ValidationExceptionDetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {
  
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre){
    return new ResponseEntity<>(
      BadRequestExceptionDetails.builder()
      .timestamp(LocalDateTime.now())
      .status(HttpStatus.BAD_REQUEST.value())
      .title("Bad Request Exception, Check the Documentation")
      .details(bre.getMessage())
      .developerMessage(bre.getClass().getName())
      .build(), HttpStatus.BAD_REQUEST
    );
    
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationExceptionDetails> 
    handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
      //log.info("Fields: {}",exception.getBindingResult().getFieldError().getField());

      String fields =  exception.getBindingResult()
                      .getFieldErrors().stream()
                      .map(FieldError::getField)
                      .collect(Collectors.joining(", "));

      String fieldsMessage =  exception.getBindingResult()
                      .getFieldErrors().stream()
                      .map(FieldError::getDefaultMessage)
                      .collect(Collectors.joining(", "));

      return new ResponseEntity<>(
          ValidationExceptionDetails.builder()
          .timestamp(LocalDateTime.now())
          .status(HttpStatus.BAD_REQUEST.value())
          .title("Bad Request Exception, Invalid Fields")
          .details("Check the Field(s) error")
          .developerMessage(exception.getClass().getName())
          .fields(fields)
          .fieldsMessage(fieldsMessage)
          .build(), HttpStatus.BAD_REQUEST
      );
    
  }
}

/**
 * to figure out some exception, on the postman you can POST  http://localhost:8080/cars?trace=true
 * and in this application, don't forget to put null in one of parameter to generate exception.
 */
