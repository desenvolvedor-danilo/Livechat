package com.dkmo.living_chatting.infrastructure.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dkmo.living_chatting.application.gateway.FindUserGateway;
import com.dkmo.living_chatting.application.gateway.ValidateTokenGateway;
import com.dkmo.living_chatting.domain.model.User;
import com.dkmo.living_chatting.infrastructure.adapters.UserEntityAdapter;
import com.dkmo.living_chatting.infrastructure.persistence.UserEntity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class FilterRequests extends OncePerRequestFilter{
@Autowired
private ValidateTokenGateway validateTokenGateway;
@Autowired
private FindUserGateway findUserGateway;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{ 
    String token = extractToken(request);
    try{
    if (token == null) {
    filterChain.doFilter(request, response);
    return;
    }
      String email = validateTokenGateway.validateToken(token);
    User user = findUserGateway.findByEmail(email);
    UserEntity userEntity = UserEntityAdapter.toUserEntity(user); 
    // if(userEntity==null){
    //     SecurityContextHolder.clearContext();
    //   }
    Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity,token,userEntity.getAuthorities());

     SecurityContextHolder.getContext().setAuthentication(authentication);
    
    }catch(Exception exception){
    SecurityContextHolder.clearContext();
    }
    filterChain.doFilter(request, response);
}

  private String extractToken(HttpServletRequest request){
    if(request.getCookies()==null){
      return null;
    }
    for(Cookie cookie : request.getCookies()){
      if("token".equals(cookie.getName())){
      return cookie.getValue();
      }
    }
   return null;
}
}
