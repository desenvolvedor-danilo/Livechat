package com.dkmo.living_chatting.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.living_chatting.application.usecases.DeleteMessageUseCase;
import com.dkmo.living_chatting.application.usecases.LoadMessageUseCase;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.adapters.ListMessagesAdapter;

@RestController
@RequestMapping("/private-messages")
public class MessagePrivateController {
  private final LoadMessageUseCase loadMessageUseCase;
  private final DeleteMessageUseCase deleteMessageUseCase;

  /**
   * @param loadMessageUseCase
   */
  public MessagePrivateController(LoadMessageUseCase loadMessageUseCase, DeleteMessageUseCase deleteMessageUseCase) {
    this.loadMessageUseCase = loadMessageUseCase;
    this.deleteMessageUseCase = deleteMessageUseCase;
  }

  @GetMapping("/find")
  public List<ListMessagesAdapter> getMessages(@RequestParam("to") String to, @RequestParam("from") String from) {
    List<Message> messages = loadMessageUseCase.execute(to, from);
    return messages.stream().map(ListMessagesAdapter::convertResponse).toList();
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteMessage(@RequestParam(name = "id") String id) {
    deleteMessageUseCase.deleteMessageUseCase(UUID.fromString(id));
    return ResponseEntity.ok("Excluido com sucesso");
  }
}
