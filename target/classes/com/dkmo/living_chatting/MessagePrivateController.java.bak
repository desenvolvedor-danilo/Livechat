package com.dkmo.living_chatting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private-messages")
public class MessagePrivateController {
  @Autowired
  private MessagePrivateService messagePrivateService;
  @GetMapping("/to")
  public List<MessagesPrivate>  getMessagesByTo(@RequestParam(name = "email") String to){
  return messagePrivateService.getMessageByTo(to);
  } 
  @GetMapping("/find")
  public List<MessagesPrivate> getMessages(@RequestParam("to") String to,@RequestParam("from") String from){
    return messagePrivateService.findAllMessagesPrivate(to, from);
  }
}
