package com.dkmo.living_chatting.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.dkmo.living_chatting.application.usecases.MessageUseCase;
import com.dkmo.living_chatting.controller.DTOs.MessageRequestDTO;

@Controller
public class MsgController {
  private final MessageUseCase messageUseCase;

  public MsgController(MessageUseCase messageUseCase) {
    this.messageUseCase = messageUseCase;
  }

  @MessageMapping("/chat/private/")
  public ResponseEntity<?> sendMessage(@Payload MessageRequestDTO message, Principal principal) {
    // System.out.println(message);
    messageUseCase.execute(message.message(), principal.getName(), message.to(), message.user(), message.urlFile());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  // @DeleteMapping("/chat/private/delete")
  // public ResponseEntity<?> deleteMessage(@Payload MessageRequestDTO message,
  // Principal principal) {
  //
  // }
}
