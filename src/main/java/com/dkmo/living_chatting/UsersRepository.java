package com.dkmo.living_chatting;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsersRepository extends MongoRepository<UserModel,String> {
UserModel findByEmail(String email);
}
