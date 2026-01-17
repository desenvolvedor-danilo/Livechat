package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.User;

public interface LoginPolicyGateway {
User findByEmail(String email);
}
