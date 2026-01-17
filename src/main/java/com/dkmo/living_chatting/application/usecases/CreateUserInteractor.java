package com.dkmo.living_chatting.application.usecases;
import com.dkmo.living_chatting.application.gateway.UserGateway;
import com.dkmo.living_chatting.domain.model.User;
public class CreateUserInteractor {
  private final UserGateway userGateway;

  /**
   * @param userGateway
   */
  public CreateUserInteractor(UserGateway userGateway) {
    this.userGateway = userGateway;
  }

  public User createUser(User user){
    return userGateway.createUser(user);
  }
}
