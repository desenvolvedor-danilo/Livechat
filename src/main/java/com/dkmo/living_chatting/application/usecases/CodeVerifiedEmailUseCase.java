package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.CodeEmailVerifiedGateway;
import com.dkmo.living_chatting.application.gateway.DeleteHashByIdGateway;
import com.dkmo.living_chatting.application.gateway.GenerateHashGateway;
import com.dkmo.living_chatting.application.gateway.SaveHashCodeVerifyGateway;
import com.dkmo.living_chatting.application.inputs.CodeVerifiedEmailInput;
import com.dkmo.living_chatting.application.output.CodeVerifiedEmailResponseMessage;
import com.dkmo.living_chatting.domain.model.CodeModel;

public class CodeVerifiedEmailUseCase {
  private final CodeEmailVerifiedGateway codeEmailVerifiedGateway;
  private final GenerateHashGateway generateHashGateway;
  private final SaveHashCodeVerifyGateway saveHashCodeVerifyGateway;

  /**
   * @param codeEmailVerifiedGateway
   * @param generateHashGateway
   * @param saveHashCodeVerifyGateway
   */
  public CodeVerifiedEmailUseCase(CodeEmailVerifiedGateway codeEmailVerifiedGateway,
      GenerateHashGateway generateHashGateway, SaveHashCodeVerifyGateway saveHashCodeVerifyGateway) {
    this.codeEmailVerifiedGateway = codeEmailVerifiedGateway;
    this.generateHashGateway = generateHashGateway;
    this.saveHashCodeVerifyGateway = saveHashCodeVerifyGateway;
  }

  public CodeVerifiedEmailResponseMessage execute(String recipient) {

    CodeVerifiedEmailInput codeVerifiedEmailInput = new CodeVerifiedEmailInput(recipient);
    var codeVerifiedEmailResponseMessage = codeEmailVerifiedGateway.verify(codeVerifiedEmailInput);
    String hashCode = generateHashGateway
        .generateHash(Integer.toString(codeVerifiedEmailResponseMessage.verifiedNumber()));
    CodeModel codeModel = CodeModel.create(hashCode);
    saveHashCodeVerifyGateway.saveHashCode(codeModel);
    return codeVerifiedEmailResponseMessage;

  }
}
