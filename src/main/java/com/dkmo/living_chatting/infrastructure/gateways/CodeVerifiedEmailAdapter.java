package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.dkmo.living_chatting.application.gateway.CodeEmailVerifiedGateway;
import com.dkmo.living_chatting.application.inputs.CodeVerifiedEmailInput;
import com.dkmo.living_chatting.application.output.CodeVerifiedEmailResponseMessage;

@Component
public class CodeVerifiedEmailAdapter implements CodeEmailVerifiedGateway {
  @Autowired
  private RestClient restClient;

  @Override
  public CodeVerifiedEmailResponseMessage verify(CodeVerifiedEmailInput codeVerifiedEmailInput) {
    // try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor())
    // {
    // executor.submit(() -> {

    CodeVerifiedEmailResponseMessage codeVerifiedEmailResponseMessage = restClient.post().uri("/enviar/email")
        .body(codeVerifiedEmailInput).retrieve().body(CodeVerifiedEmailResponseMessage.class);
    return codeVerifiedEmailResponseMessage;
    // });
    // } catch (Exception e) {
    // throw new RuntimeException(e.getMessage());
    // }
    // return null;
  }
}
