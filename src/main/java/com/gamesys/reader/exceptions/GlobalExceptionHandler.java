package com.gamesys.reader.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gamesys.reader.model.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler{

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Global Exception handler for all exceptions.
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorMessage> handle(Exception exception) {
    LOG.error("Exception: Unable to process this request. ", exception);
    ErrorMessage errorMessage = new ErrorMessage("Unable to process this request. ",exception.getMessage());
    
    return new ResponseEntity<ErrorMessage>(errorMessage,new HttpHeaders(),HttpStatus.BAD_REQUEST);
  }
}
