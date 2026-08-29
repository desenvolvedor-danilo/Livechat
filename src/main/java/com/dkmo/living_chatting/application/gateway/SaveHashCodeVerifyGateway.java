package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.CodeModel;

public interface SaveHashCodeVerifyGateway {
  void saveHashCode(CodeModel codeModel);
}
