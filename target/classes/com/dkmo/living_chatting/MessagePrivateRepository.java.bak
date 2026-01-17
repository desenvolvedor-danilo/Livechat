package com.dkmo.living_chatting;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MessagePrivateRepository extends MongoRepository<MessagesPrivate,String> {
List<MessagesPrivate> findByParticipantes(String to,String from);
List<MessagesPrivate> findByTo(String to);
long countByFrom(String from);  
}
