package com.dkmo.living_chatting.domain.model;

public class AbstractAuthorization {
private String token;
private String refreshToken;

  public AbstractAuthorization(String token, String refreshToken) {
  this.token = token;
  this.refreshToken = refreshToken;
}

  public String token() {
    return token;
  }

  public String refreshToken() {
    return refreshToken;
  }

}
