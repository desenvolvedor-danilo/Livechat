package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.UserNotFoundExceptions;
import com.dkmo.living_chatting.application.gateway.EncryptPasswordGateway;
import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.application.gateway.PasswordRedifineGateway;
import com.dkmo.living_chatting.domain.model.User;

public class ChangePasswordUseCase {
  private final PasswordRedifineGateway passwordRedifineGateway;
  private final FindUserGateway findUserGateway;
  private final EncryptPasswordGateway encryptPasswordGateway;

  /**
   * @param passwordRedifineGateway
   */
  public ChangePasswordUseCase(PasswordRedifineGateway passwordRedifineGateway, FindUserGateway findUserGateway,
      EncryptPasswordGateway encryptPasswordGateway) {
    this.passwordRedifineGateway = passwordRedifineGateway;
    this.findUserGateway = findUserGateway;
    this.encryptPasswordGateway = encryptPasswordGateway;
  }

  public void execute(String email, String rawPassword) {
    User user = findUserGateway.findByEmail(email);

    if (user == null) {
      throw new UserNotFoundExceptions();
    }
    String encryptedPassword = encryptPasswordGateway.passwordEncrypt(rawPassword);

    passwordRedifineGateway.changePassword(email, encryptedPassword);
  }
}
