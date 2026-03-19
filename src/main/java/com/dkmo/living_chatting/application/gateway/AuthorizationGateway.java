package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.AbstractAuthorization;
import com.dkmo.living_chatting.domain.model.User;

public interface AuthorizationGateway {
AbstractAuthorization generateToken(User user);
}
