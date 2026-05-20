package com.dkmo.living_chatting.domain.model;

import java.time.Instant;
import java.util.UUID;

public record RefreshToken(UUID id, UUID idUser, String hashToken, Instant expiresAt,Instant createdAt) 
{
  public static RefreshToken create(UUID idUser, String hash,long hours){

Instant now = Instant.now();
return new RefreshToken(UUID.randomUUID(), idUser,  hash, Instant.now().plusSeconds(hours*3600), now);
  }

  public boolean isExpired(){
    return Instant.now().isAfter(expiresAt);
  }
  public boolean matches(String hash){
    return hashToken.equals(hash);
  }
}
