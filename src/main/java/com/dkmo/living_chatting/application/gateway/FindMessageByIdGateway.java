package com.dkmo.living_chatting.application.gateway;

import java.util.Optional;
import java.util.UUID;

import com.dkmo.living_chatting.domain.model.Message;

public interface FindMessageByIdGateway {
  Optional<Message> findById(UUID id);
}
