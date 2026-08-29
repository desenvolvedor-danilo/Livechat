package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.SaveHashCodeVerifyGateway;
import com.dkmo.living_chatting.domain.model.CodeModel;
import com.dkmo.living_chatting.infrastructure.dto.CodeModelToCodeEntity;
import com.dkmo.living_chatting.infrastructure.repositories.CodeVerifyRepository;

@Component
public class SaveHashCode implements SaveHashCodeVerifyGateway {
  @Autowired
  private CodeVerifyRepository codeVerifyRepository;

  @Override
  public void saveHashCode(CodeModel codeModel) {
    codeVerifyRepository.save(CodeModelToCodeEntity.convertModelToEntity(codeModel));

  }
}
