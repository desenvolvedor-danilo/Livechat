package com.dkmo.living_chatting.application.gateway;

import java.util.List;

import com.dkmo.living_chatting.domain.model.Message;

public interface LoadMessagesGateway {
List<Message> findMessagesBySender(String sender,String recipient);
}
