package com.dkmo.living_chatting.infrastructure.adapters;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dkmo.living_chatting.application.usecases.FindUserDetailsByEmail;
import com.dkmo.living_chatting.domain.model.User;

@Service
public class CustomUserDetailsMapper implements UserDetailsService{
  private final FindUserDetailsByEmail findUserDetailsByEmail;

public CustomUserDetailsMapper(FindUserDetailsByEmail findUserDetailsByEmail) {
    this.findUserDetailsByEmail = findUserDetailsByEmail;
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User user = findUserDetailsByEmail.execute(username);
  
  return UserEntityAdapter.toUserEntity(user);
  }

}
