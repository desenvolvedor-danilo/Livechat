package com.dkmo.living_chatting.application.usecases;

import java.util.List;

import com.dkmo.living_chatting.application.gateway.LoadMessagesGateway;
import com.dkmo.living_chatting.domain.model.Message;

public class LoadMessageUseCase {
private final LoadMessagesGateway loadMessagesGateway;
  public LoadMessageUseCase(LoadMessagesGateway loadMessagesGateway){
this.loadMessagesGateway = loadMessagesGateway;
  }

  public List<Message> execute(String sender,String recipient){
  return loadMessagesGateway.findMessagesBySender(sender,recipient);
  } 
} 
