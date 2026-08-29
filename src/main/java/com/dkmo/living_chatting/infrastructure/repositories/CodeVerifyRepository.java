package com.dkmo.living_chatting.infrastructure.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dkmo.living_chatting.infrastructure.persistence.CodeVerifyEntity;

@Repository
public interface CodeVerifyRepository extends MongoRepository<CodeVerifyEntity, String> {
  CodeVerifyEntity findByHash(String hash);
}
