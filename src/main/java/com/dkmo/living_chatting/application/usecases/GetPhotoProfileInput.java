package com.dkmo.living_chatting.application.usecases;

public class GetPhotoProfileInput {
private final String email;

public GetPhotoProfileInput(String email) {
  this.email = email;
}

/**
 * @return the email
 */
public String email() {
  return email;
}

 
}

