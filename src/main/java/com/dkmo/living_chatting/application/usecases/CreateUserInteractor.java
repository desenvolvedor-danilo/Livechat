package com.dkmo.living_chatting.application.usecases;
import java.util.UUID;

import com.dkmo.living_chatting.application.exceptions.EmailInvalidException;
import com.dkmo.living_chatting.application.gateway.EncryptPasswordGateway;
import com.dkmo.living_chatting.application.gateway.GenerateIdGateway;
import com.dkmo.living_chatting.application.gateway.UserGateway;
import com.dkmo.living_chatting.application.inputs.InputCreateUser;
import com.dkmo.living_chatting.domain.model.User;
public class CreateUserInteractor {
  private final UserGateway userGateway;
  private final EncryptPasswordGateway encryptPasswordGateway;
  private final GenerateIdGateway generateIdGateway;

  /**
   * @param userGateway
   */
  public CreateUserInteractor(UserGateway userGateway,EncryptPasswordGateway encryptPasswordGateway,GenerateIdGateway generateIdGateway) {
    this.userGateway = userGateway;
    this.encryptPasswordGateway = encryptPasswordGateway;
    this.generateIdGateway = generateIdGateway;
  }

  public User createUser(InputCreateUser inputCreateUser){
  if(!inputCreateUser.email().matches("^[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+(\\.[a-z]+)?$")){
throw new EmailInvalidException();
}
  String passwordEncoded = encryptPasswordGateway.passwordEncrypt(inputCreateUser.senha());
   UUID id = generateIdGateway.generateId();
    User user = User.create(inputCreateUser.nome(), inputCreateUser.email(), passwordEncoded, inputCreateUser.usuario());
    user.defineId(id);
    return userGateway.createUser(user);
  }
}
