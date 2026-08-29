package com.dkmo.living_chatting.infrastructure.dto;

import java.util.UUID;

import com.dkmo.living_chatting.domain.model.CodeModel;
import com.dkmo.living_chatting.infrastructure.persistence.CodeVerifyEntity;

public class CodeModelToCodeEntity {

  public static CodeVerifyEntity convertModelToEntity(CodeModel codeModel) {
    return new CodeVerifyEntity(codeModel.id().toString(), codeModel.hash());
  }

  public static CodeModel convertEntityToModel(CodeVerifyEntity codeVerifyEntity) {
    if (codeVerifyEntity == null) {
      return null;
    }
    CodeModel codeModel = CodeModel.create(UUID.fromString(codeVerifyEntity.getId()), codeVerifyEntity.getHash());
    return codeModel;

  }
}
