package com.dkmo.living_chatting.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.living_chatting.application.usecases.LoadMessageUseCase;
import com.dkmo.living_chatting.domain.model.Message;

@RestController
@RequestMapping("/private-messages")
public class MessagePrivateController {
  private final LoadMessageUseCase loadMessageUseCase;

  /**
   * @param loadMessageUseCase
   */
  public MessagePrivateController(LoadMessageUseCase loadMessageUseCase) {
    this.loadMessageUseCase = loadMessageUseCase;
  }

  // @GetMapping("/to")
  // public List<MessagesPrivate>  getMessagesByTo(@RequestParam(name = "email") String to){
  // return messagePrivateService.getMessageByTo(to);
  // } 
  @GetMapping("/find")
  public List<Message> getMessages(@RequestParam("to") String to,@RequestParam("from") String from){
  return loadMessageUseCase.execute(to, from);
  }
}
