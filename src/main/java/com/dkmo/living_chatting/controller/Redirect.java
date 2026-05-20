package com.dkmo.living_chatting.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/redirect")
public class Redirect {
  @GetMapping("/page")
  public void redirect(@RequestParam("user") String user,HttpServletResponse response)throws IOException{
response.sendRedirect("http://localhost:3000/chat?user="+user);
  }

  
}
