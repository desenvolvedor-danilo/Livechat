package com.dkmo.living_chatting.application.gateway;

import java.util.List;

import com.dkmo.living_chatting.domain.model.Conversation;

public interface LoadNotificationGateway {
List<Conversation> loadConversation(String from);
}
