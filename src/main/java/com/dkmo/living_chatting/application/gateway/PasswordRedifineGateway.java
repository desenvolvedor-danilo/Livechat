package com.dkmo.living_chatting.application.gateway;

public interface PasswordRedifineGateway {
  void changePassword(String email, String rawPassword);
}
