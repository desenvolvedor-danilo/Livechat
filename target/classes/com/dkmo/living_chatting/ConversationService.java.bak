package com.dkmo.living_chatting;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
  @Autowired
  private ConversationRepository conversationRepository;
  @Autowired
  private MongoTemplate mongoTemplate; 
   public void Conversation(MessagesPrivate messagesPrivate){
    
    Optional<Conversation> chat = conversationRepository.findById(messagesPrivate.getIdConversa());
    if(chat.isPresent()){
     Query query = new Query(Criteria.where("id").is(chat.get().getId()));
      Update update = new Update().set("lastMessage",messagesPrivate.getMessage()).set("updatedAt", messagesPrivate.getTimeStamp());
      mongoTemplate.updateFirst(query, update,Conversation.class);
      
    }else{  
Conversation conversation = new Conversation();
    conversation.setId(messagesPrivate.getIdConversa());
    conversation.setName(messagesPrivate.getName());
    conversation.addParticipantes(messagesPrivate.getFrom(), messagesPrivate.getTo());
    conversation.setLastMessage(messagesPrivate.getMessage());
    conversation.setLastSender(messagesPrivate.getName());
    conversation.setUpdatedAt(messagesPrivate.getTimeStamp());
    conversationRepository.save(conversation);
    }
  }
    public List<Conversation> getLastConversation(String from){

     List<Conversation> conversa = conversationRepository.findByParticipantes(from);
        return conversa; 
    }
}
