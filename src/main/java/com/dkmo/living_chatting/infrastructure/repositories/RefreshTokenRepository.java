package com.dkmo.living_chatting.infrastructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dkmo.living_chatting.infrastructure.persistence.RefreshTokenEntity;

public interface RefreshTokenRepository extends MongoRepository<RefreshTokenEntity,String> {
void deleteByHashToken(String hash); 
}
