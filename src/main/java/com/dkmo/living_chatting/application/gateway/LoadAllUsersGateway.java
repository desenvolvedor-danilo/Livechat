package com.dkmo.living_chatting.application.gateway;

import java.util.List;

import com.dkmo.living_chatting.domain.model.User;

public interface LoadAllUsersGateway {
 List<User> findAllUsers();
}
