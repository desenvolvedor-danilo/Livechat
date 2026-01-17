package com.dkmo.living_chatting.infrastructure.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dkmo.living_chatting.infrastructure.persistence.MessagesPrivate;
@Repository
public interface MessagePrivateRepository extends MongoRepository<MessagesPrivate,String> {
List<MessagesPrivate> findByParticipantes(String to,String from);
List<MessagesPrivate> findByTo(String to);
long countByFrom(String from);  
}
