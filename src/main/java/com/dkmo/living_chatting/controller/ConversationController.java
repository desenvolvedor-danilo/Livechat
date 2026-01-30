package com.dkmo.living_chatting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.living_chatting.application.usecases.LoadNotificationsUseCase;
import com.dkmo.living_chatting.controller.DTOs.ConversationResponseDto;
import com.dkmo.living_chatting.domain.model.Conversation;

@RestController
@RequestMapping("/conversas")
public class ConversationController {
  @Autowired
  private LoadNotificationsUseCase loadNotificationsUseCase;
  @GetMapping("/private")
 public List<ConversationResponseDto>
loadConversation(@RequestParam(name = "from") String sender){
  List<Conversation> conversations = loadNotificationsUseCase.loadConversation(sender);
    return conversations.stream().map(ConversationResponseDto::create).toList();
  }
}
