package com.dkmo.living_chatting.infrastructure.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dkmo.living_chatting.infrastructure.persistence.ConversationEntity;

@Repository
public interface ConversationRepository extends MongoRepository<ConversationEntity,String>{
 // Conversation findByTo(String to);
List<ConversationEntity> findByParticipantes(String from);
 // Conversation findByFrom(String from);
}
