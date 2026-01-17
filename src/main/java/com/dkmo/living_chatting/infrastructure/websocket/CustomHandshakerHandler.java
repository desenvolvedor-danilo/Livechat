package com.dkmo.living_chatting.infrastructure.websocket;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

public class CustomHandshakerHandler extends DefaultHandshakeHandler {
  @Override
  public Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
      Map<String, Object> atributtes) {
    String query = request.getURI().getQuery();
    if (query != null && query.startsWith("user=")) {
      String username = query.substring(5);
      return () -> username;
    }
    return () -> "anon-" + UUID.randomUUID();
  }

}
