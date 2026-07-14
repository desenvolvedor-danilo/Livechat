package com.dkmo.living_chatting.application.usecases;

import java.util.Optional;
import java.util.UUID;

import com.dkmo.living_chatting.application.exceptions.NotFoundMessageException;
import com.dkmo.living_chatting.application.gateway.DeleteMessageGateway;
import com.dkmo.living_chatting.application.gateway.FindMessageByIdGateway;
import com.dkmo.living_chatting.application.gateway.DeleteMessageCallbackGateway;
import com.dkmo.living_chatting.domain.model.Message;

public class DeleteMessageUseCase {
  private final DeleteMessageGateway deleteMessageGateway;
  private final FindMessageByIdGateway findMessageByIdGateway;
  private final DeleteMessageCallbackGateway deleteMessageCallbackGateway;

  /**
   * @param deleteMessageGateway
   */
  public DeleteMessageUseCase(DeleteMessageGateway deleteMessageGateway,
      FindMessageByIdGateway findMessageByIdGateway, DeleteMessageCallbackGateway deleteMessageCallbackGateway) {
    this.deleteMessageGateway = deleteMessageGateway;
    this.findMessageByIdGateway = findMessageByIdGateway;
    this.deleteMessageCallbackGateway = deleteMessageCallbackGateway;
  }

  public void deleteMessageUseCase(UUID id) {
    Optional<Message> msg = findMessageByIdGateway.findById(id);
    if (!msg.isPresent()) {
      throw new NotFoundMessageException();
    }
    deleteMessageCallbackGateway.deleteMessageCallback(msg.get().getTo(), msg.get().getId());
    deleteMessageGateway.deleteMessage(msg.get());
  }
}
