package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.dkmo.living_chatting.application.gateway.EditUserGateway;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

public class EditUserGatewayImpl implements EditUserGateway {
  private final MongoTemplate mongoTemplate;


  /**
   * @param mongoTemplate
   */
  public EditUserGatewayImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }


  @Override
  public void editPhotoProfile(User user, String url) {
   Query query = new Query(Criteria.where("email").is(user.email()));
   Update update = new Update();
    update.set("photoProfile", url);
    mongoTemplate.updateFirst(query, update, UserEntity.class);
  }
    
}
