package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.FcmTokenGateway;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

@Component
public class FcmTokenGatewayImpl implements FcmTokenGateway{
@Autowired
  private MongoTemplate mongoTemplate;
  @Override
  public void defineFcmToken(String email,String token) {
  Query query = new Query(Criteria.where("email").is( email));
  Update update = new Update().set("fcmToken", token);
  mongoTemplate.updateFirst(query, update, UserEntity.class);
  }

}
