package com.dkmo.living_chatting.application.usecases;

import com.dkmo.living_chatting.application.gateway.FcmTokenGateway;

public class FcmTokenUseCase {
private final FcmTokenGateway fcmTokenGateway;
/**
 * @param fcmTokenGateway
 */
public FcmTokenUseCase(FcmTokenGateway fcmTokenGateway) {
  this.fcmTokenGateway = fcmTokenGateway;
}
public void execute(String email,String token){
  fcmTokenGateway.defineFcmToken(email,token);
    
  }
}
