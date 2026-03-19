package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.GenerateCookieGateway;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieGatewayImpl implements GenerateCookieGateway {
private final HttpServletResponse response;


  /**
 * @param response
 */
public CookieGatewayImpl(HttpServletResponse response) {
  this.response = response;
}
   
  @Override
  public void write(String name, String value) {
  Cookie cookie = new Cookie(name,value);
  cookie.setHttpOnly(true);
  cookie.setPath("/");
  response.addCookie(cookie);
  }

  
}
