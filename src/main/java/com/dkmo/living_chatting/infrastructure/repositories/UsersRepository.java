package com.dkmo.living_chatting.infrastructure.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;
@Repository
public interface UsersRepository extends MongoRepository<UserEntity,String> {
UserEntity findByEmail(String email);
}
