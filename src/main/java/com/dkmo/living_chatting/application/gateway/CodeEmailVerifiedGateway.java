package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.application.inputs.CodeVerifiedEmailInput;
import com.dkmo.living_chatting.application.output.CodeVerifiedEmailResponseMessage;

public interface CodeEmailVerifiedGateway {
  CodeVerifiedEmailResponseMessage verify(CodeVerifiedEmailInput codeVerifiedEmailInput);

}
