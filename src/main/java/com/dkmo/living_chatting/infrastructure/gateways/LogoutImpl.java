package com.dkmo.living_chatting.infrastructure.gateways;

import org.springframework.stereotype.Component;

import com.dkmo.living_chatting.application.gateway.LogoutGateway;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutImpl implements LogoutGateway {
  private HttpServletResponse response;

  /**
   * @param response
   */
  public LogoutImpl(HttpServletResponse response) {
    this.response = response;
  }

  @Override
  public void Logout() {

    Cookie cookie = new Cookie("token", null);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(0);

    Cookie cookieRefresh = new Cookie("refresh-token", null);
    cookieRefresh.setPath("/");
    cookieRefresh.setHttpOnly(true);
    cookieRefresh.setMaxAge(0);
    response.addCookie(cookie);
    response.addCookie(cookieRefresh);

  }
}
