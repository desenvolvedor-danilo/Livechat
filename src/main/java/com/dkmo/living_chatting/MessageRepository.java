package com.dkmo.living_chatting;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageModel, String> {

}
