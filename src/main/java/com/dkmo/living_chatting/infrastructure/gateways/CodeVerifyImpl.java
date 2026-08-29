package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.FindByHashCodeGateway;
import com.dkmo.living_chatting.domain.model.CodeModel;
import com.dkmo.living_chatting.infrastructure.dto.CodeModelToCodeEntity;
import com.dkmo.living_chatting.infrastructure.persistence.CodeVerifyEntity;
import com.dkmo.living_chatting.infrastructure.repositories.CodeVerifyRepository;

@Component
public class CodeVerifyImpl implements FindByHashCodeGateway {
  @Autowired
  private CodeVerifyRepository codeVerifyRepository;

  @Override
  public CodeModel findByCodeHash(String hash) {
    CodeVerifyEntity codeVerifyEntity = codeVerifyRepository.findByHash(hash);
    return CodeModelToCodeEntity.convertEntityToModel(codeVerifyEntity);
  }

}
