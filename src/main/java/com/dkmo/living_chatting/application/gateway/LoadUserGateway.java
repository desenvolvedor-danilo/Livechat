package com.dkmo.living_chatting.application.gateway;

import com.dkmo.living_chatting.domain.model.User;

public interface LoadUserGateway {
    User findUserByEmail(String sender);
}

