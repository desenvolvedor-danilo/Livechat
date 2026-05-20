package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.RefreshToken;

public interface SaveRefreshTokenGateway {
void save(RefreshToken refreshToken);
}
