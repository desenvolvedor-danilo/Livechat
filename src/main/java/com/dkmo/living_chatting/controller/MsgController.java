package com.dkmo.living_chatting.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.dkmo.living_chatting.application.usecases.MessageUseCase;

@Controller
public class MsgController {
 private final MessageUseCase messageUseCase;
  public MsgController(MessageUseCase messageUseCase){
    this.messageUseCase= messageUseCase;
  }
@MessageMapping("/chat/private/") 
public ResponseEntity<?> sendMessage(@Payload com.dkmo.living_chatting.controller.DTOs.MessageRequestDTO message,Principal principal){    
    messageUseCase.execute(message.message(),principal.getName(),message.to(),message.user());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
