package com.dkmo.living_chatting.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dkmo.living_chatting.infrastructure.filter.FilterRequests;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
// @Autowired
// private FilterRequests filter;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration
    configuration)throws Exception{
        AuthenticationManager authentication = configuration.getAuthenticationManager();
    return http.csrf(csrf->csrf.disable())
    .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(authorize->authorize
    .requestMatchers(HttpMethod.POST,"/users/login").  permitAll()
     .requestMatchers(HttpMethod.POST,"/users/create").permitAll() 
    .requestMatchers("/login", "/login.html"). permitAll()
    .requestMatchers("/favicon.png").permitAll()
    .requestMatchers("/list-contacts.css").permitAll()
    .requestMatchers("/css/**").permitAll()
    .requestMatchers("/notifications","/notifications.html").permitAll()
    .requestMatchers("/main.css").permitAll()
    .requestMatchers("/uploads/photos-profiles/**","/photos-profiles/**").permitAll()

    .requestMatchers("/imagens/**").permitAll()
    .requestMatchers("/cadastro.html").permitAll()
    .requestMatchers(HttpMethod.POST,"/update/password").permitAll()

    .requestMatchers("/app/chat/private").permitAll()
   
    .requestMatchers("/private.html").permitAll()
    .requestMatchers("/buildrun-livechat-websocket/**").permitAll()
    .requestMatchers("/contacts", "/list-contacts.html").permitAll()
    .requestMatchers("/app.js").permitAll()
    
    .requestMatchers("/js/**").permitAll()
    .anyRequest().authenticated())
    .addFilterBefore(new FilterRequests(authentication),UsernamePasswordAuthenticationFilter.class)
    // .httpBasic(Customizer.withDefaults())
    .build();
  } 
}
