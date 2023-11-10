package com.glowat.openkkutuweb.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ExceptionHandler {
  private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

  @org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
  public String handleException(Exception e) {
    logger.error("Got unhandled exception : ", e);
    return "{\"error\":470}";
  }

}
