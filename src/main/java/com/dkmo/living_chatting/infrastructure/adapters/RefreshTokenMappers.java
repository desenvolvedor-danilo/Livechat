package com.dkmo.living_chatting.infrastructure.adapters;

import com.dkmo.living_chatting.domain.model.RefreshToken;
import com.dkmo.living_chatting.infrastructure.persistence.RefreshTokenEntity;

public class RefreshTokenMappers {
public static RefreshTokenEntity refreshTokenToRefreshTokenEntity(RefreshToken refreshToken){
    return new RefreshTokenEntity(refreshToken.id().toString(),refreshToken.idUser().toString(),refreshToken.hashToken(),refreshToken.expiresAt(),refreshToken.createdAt());
  }

}
