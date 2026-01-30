package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.ConversationEditGateway;
import com.dkmo.living_chatting.domain.model.Conversation;
import com.dkmo.living_chatting.domain.model.Message;
import com.dkmo.living_chatting.infrastructure.persistence.ConversationEntity;
@Component
public class EditConversation implements ConversationEditGateway {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void editConversation(Conversation conversation, Message message) {
   Query query = new Query(Criteria.where("id").is(conversation.getId()));
    Update update = new Update().set("lastMessage", message.getMessage()).set("updateAt", message.getNow());
    mongoTemplate.updateFirst(query, update, ConversationEntity.class);
  }
    
}
