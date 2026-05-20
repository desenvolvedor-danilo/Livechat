package com.dkmo.living_chatting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkmo.living_chatting.application.usecases.RefreshTokenUseCase;

@RestController 
@RequestMapping("/refresh")
public class RefreshTokenController {
@Autowired
private RefreshTokenUseCase refreshTokenUseCase;
@PostMapping("/token")
public void RefreshToken(@CookieValue(name = "refresh-token")String refreshToken){
 refreshTokenUseCase.execute(refreshToken);
  }
}
