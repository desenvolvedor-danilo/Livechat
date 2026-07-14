package com.dkmo.living_chatting.application.gateway;

import java.util.UUID;

public interface DeleteMessageCallbackGateway {
  void deleteMessageCallback(String destinatario, UUID id);
}
