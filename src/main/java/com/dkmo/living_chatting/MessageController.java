package com.dkmo.living_chatting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
  @Autowired
  private MessageRepository messageRepository;

  @GetMapping("/findall")
  public List<MessageModel> getAllMessages(){


  return messageRepository.findAll();

  }
}
