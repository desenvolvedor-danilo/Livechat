package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.exceptions.HashCodeNotFoundException;
import com.dkmo.living_chatting.application.gateway.DeleteHashByIdGateway;
import com.dkmo.living_chatting.application.gateway.FindByHashCodeGateway;
import com.dkmo.living_chatting.application.gateway.GenerateHashGateway;
import com.dkmo.living_chatting.domain.model.CodeModel;

public class VerifyCodeHashUseCase {
  private final FindByHashCodeGateway findByHashCodeGateway;
  private final GenerateHashGateway generateHashGateway;

  private final DeleteHashByIdGateway deleteHashByIdGateway;

  /**
   * @param findByHashCodeGateway
   */
  public VerifyCodeHashUseCase(FindByHashCodeGateway findByHashCodeGateway, GenerateHashGateway generateHashGateway,
      DeleteHashByIdGateway deleteHashByIdGateway) {
    this.findByHashCodeGateway = findByHashCodeGateway;
    this.generateHashGateway = generateHashGateway;
    this.deleteHashByIdGateway = deleteHashByIdGateway;
  }

  public void execute(String code) {
    String hash = generateHashGateway.generateHash(code);
    CodeModel codeModel = findByHashCodeGateway.findByCodeHash(hash);

    if (codeModel == null) {
      throw new HashCodeNotFoundException();
    } else {

      System.out.println(codeModel.id());
      deleteHashByIdGateway.deleteHashCode(codeModel.id().toString());
    }

  }
}
