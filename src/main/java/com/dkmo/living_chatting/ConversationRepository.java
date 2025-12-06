package com.dkmo.living_chatting;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation,String>{
 // Conversation findByTo(String to);
List<Conversation> findByParticipantes(String from);
 // Conversation findByFrom(String from);
}
