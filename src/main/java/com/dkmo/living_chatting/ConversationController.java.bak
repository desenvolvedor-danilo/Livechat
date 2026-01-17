package com.dkmo.living_chatting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversas")
public class ConversationController {
  @Autowired
  private ConversationService conversationService;
  @GetMapping("private")
    public List<Conversation> getConversation(@RequestParam("from") String from){
    return
conversationService.getLastConversation(from);

  }
}
