package com.dkmo.living_chatting.infrastructure.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterRequests extends OncePerRequestFilter{
private final AuthenticationManager authenticationManager;  
    /**
 * @param authenticationManager
 */
public FilterRequests(AuthenticationManager authenticationManager) {
  this.authenticationManager = authenticationManager;
}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{ 
    String [] token = extractToken(request);
    try{
    if (token == null) {
    filterChain.doFilter(request, response);
    return;
    }
  String email = token[0];
  String password = token[1];
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email
    ,password);
    Authentication authentication = authenticationManager.authenticate(auth);
     SecurityContextHolder.getContext().setAuthentication(authentication);
    
    }catch(Exception exception){
    SecurityContextHolder.clearContext();
    }
    filterChain.doFilter(request, response);
}

  private String[] extractToken(HttpServletRequest request){
  var header = request.getHeader("Authorization");
  if(header==null){
    return null;
  }
    
  String token =  header.replace("Basic", "").trim();
  byte[] decoded = Base64.getDecoder().decode(token);
  String emailPassword = new String(decoded,StandardCharsets.UTF_8);
  String[] credentials = emailPassword.split(":");
  return credentials;
}
}
