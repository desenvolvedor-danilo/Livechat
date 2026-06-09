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
public class FilterRequests extends OncePerRequestFilter {
  @Autowired
  private ValidateTokenGateway validateTokenGateway;
  @Autowired
  private FindUserGateway findUserGateway;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {

      String token = extractToken(request);
      if (token == null) {
        filterChain.doFilter(request, response);
        return;
      }
      System.out.println("Token enviado: " + token);
      if (token != null) {
        String email = validateTokenGateway.validateToken(token);
        if (email != null && !email.isEmpty()) {
          User user = findUserGateway.findByEmail(email);
          if (user != null) {
            UserEntity userEntity = UserEntityAdapter.toUserEntity(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, null,
                userEntity.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
          }
        }
        // filterChain.doFilter(request, response);
      }
    } catch (Exception exception) {
      // response.setStatus(401);
      // response.setContentType("application/json");
      // response.getWriter().write("{\"error\":\"unauthorized\"}");
      // return;

      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {

    if (request.getCookies() == null) {
      return null;
    }
    for (Cookie cookie : request.getCookies()) {
      if ("token".equals(cookie.getName())) {
        System.out.println(cookie.getValue());
        return cookie.getValue();
      }
    }
    return null;
  }
}
