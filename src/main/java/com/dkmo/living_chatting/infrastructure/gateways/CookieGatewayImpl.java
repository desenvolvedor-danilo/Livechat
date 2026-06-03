package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.dkmo.living_chatting.application.gateway.GenerateCookieGateway;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequestScope
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
    Cookie cookie = new Cookie(name, value);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(1000000);
    response.addCookie(cookie);
    // ResponseCookie cookie = ResponseCookie.from(name, value)
    // .secure(true)
    // .path("/")
    // .httpOnly(true)
    // .sameSite("None")
    // .maxAge(1000000)
    // .build();
    // response.addHeader("Set-Cookie", cookie.toString());
  }

}
