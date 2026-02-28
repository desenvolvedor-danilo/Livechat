package com.dkmo.living_chatting.application.usecases;
import com.dkmo.living_chatting.application.gateway.EncryptPasswordGateway;
import com.dkmo.living_chatting.application.gateway.UserGateway;
import com.dkmo.living_chatting.application.inputs.InputCreateUser;
import com.dkmo.living_chatting.domain.model.User;
public class CreateUserInteractor {
  private final UserGateway userGateway;
  private final EncryptPasswordGateway encryptPasswordGateway;

  /**
   * @param userGateway
   */
  public CreateUserInteractor(UserGateway userGateway,EncryptPasswordGateway encryptPasswordGateway) {
    this.userGateway = userGateway;
    this.encryptPasswordGateway = encryptPasswordGateway;
  }

  public User createUser(InputCreateUser inputCreateUser){
  String passwordEncoded = encryptPasswordGateway.passwordEncrypt(inputCreateUser.senha());
    User user = User.create(inputCreateUser.nome(), inputCreateUser.email(), passwordEncoded, inputCreateUser.usuario());
    return userGateway.createUser(user);
  }
}
