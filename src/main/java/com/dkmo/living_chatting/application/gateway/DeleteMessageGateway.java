package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.Message;

public interface DeleteMessageGateway {
  void deleteMessage(Message message);
}
