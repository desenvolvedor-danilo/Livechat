package com.dkmo.living_chatting.controller.handlerexceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dkmo.living_chatting.application.exceptions.ApplicationException;
import com.dkmo.living_chatting.controller.DTOs.ErrorResponse;

@RestControllerAdvice
public class GlobalHandlerExceptions {
@ExceptionHandler(ApplicationException.class)
 public ResponseEntity<ErrorResponse> handleApplication(ApplicationException ex){
    return ResponseEntity.status(401).body(new ErrorResponse(ex.getMessage()));
  }
}
 
