package com.dkmo.living_chatting.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.living_chatting.application.usecases.LoadMessageUseCase;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.adapters.ListMessagesAdapter;

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
  @GetMapping("/find")
  public List<ListMessagesAdapter> getMessages(@RequestParam("to") String to,@RequestParam("from") String from){
  List<Message> messages = loadMessageUseCase.execute(to, from);
    System.out.println(messages.stream().map(ListMessagesAdapter::convertResponse).toList());
   return messages.stream().map(ListMessagesAdapter::convertResponse).toList();
  }
}
