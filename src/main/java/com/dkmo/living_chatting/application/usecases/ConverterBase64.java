package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.EncryptBase64Gateway;

public class ConverterBase64 {
private final EncryptBase64Gateway encryptBase64Gateway;

/**
 * @param encryptBase64Gateway
 */
public ConverterBase64(EncryptBase64Gateway encryptBase64Gateway) {
  this.encryptBase64Gateway = encryptBase64Gateway;
}
public String execute(String email,String password){
    String usernamePasswordEncode = email+":"+password;
  return encryptBase64Gateway.usernamePasswordEnconder(usernamePasswordEncode);
}
}
