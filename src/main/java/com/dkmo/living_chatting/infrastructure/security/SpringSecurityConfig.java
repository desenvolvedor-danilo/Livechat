package com.dkmo.living_chatting.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dkmo.living_chatting.application.usecases.ValidateTokenUseCase;
import com.dkmo.living_chatting.infrastructure.filter.FilterRequests;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
        @Autowired
        private FilterRequests filter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration configuration,
                        ValidateTokenUseCase validate) throws Exception {
                return http.csrf(csrf -> csrf.disable())
                                .cors(cors -> {
                                })
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                                                .requestMatchers("/login", "/login.html").permitAll()
                                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                                // .requestMatchers(HttpMethod.POST,"/files/save").permitAll()
                                                .requestMatchers("/favicon.png").permitAll()
                                                .requestMatchers("/list-contacts.css").permitAll()
                                                .requestMatchers("/css/**").permitAll()
                                                .requestMatchers("/notifications", "/notifications.html").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/refresh/token").permitAll()
                                                .requestMatchers("/main.css").permitAll()
                                                .requestMatchers("/uploads/photos-profiles/**", "/photos-profiles/**")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/redirect/page").permitAll()
                                                .requestMatchers("/imagens/**").permitAll()
                                                .requestMatchers("/cadastro.html").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/update/password").permitAll()

                                                .requestMatchers("/app/chat/private").permitAll()
                                                .requestMatchers(HttpMethod.POST, "/refresh/token").permitAll()
                                                .requestMatchers("/private.html").permitAll()
                                                .requestMatchers("/configuration.html").permitAll()
                                                .requestMatchers("/icons/**").permitAll()
                                                .requestMatchers("/buildrun-livechat-websocket/**").permitAll()
                                                .requestMatchers("/contacts", "/list-contacts.html").permitAll()
                                                .requestMatchers("/app.js").permitAll()
                                                .requestMatchers("/js/**").permitAll()
                                                .anyRequest().authenticated())
                                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint((request, response, authException) -> {
                                                        // authException.printStackTrace();
                                                        response.setStatus(401);
                                                }))
                                .httpBasic(Customizer.withDefaults())
                                .build();
        }
}
