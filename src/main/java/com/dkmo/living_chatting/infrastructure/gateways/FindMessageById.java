package com.dkmo.living_chatting.infrastructure.gateways;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.FindMessageByIdGateway;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;
import com.dkmo.living_chatting.infrastructure.repositories.MessagePrivateRepository;

@Component
public class FindMessageById implements FindMessageByIdGateway {
  @Autowired
  private MessagePrivateRepository messagePrivateRepository;

  @Override
  public Optional<Message> findById(UUID id) {
    Optional<MessagesPrivate> messages = messagePrivateRepository.findById(id.toString());
    return Optional.of(messages.get().toDomain());
  }

}
