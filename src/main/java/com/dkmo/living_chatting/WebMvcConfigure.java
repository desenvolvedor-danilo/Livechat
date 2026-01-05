package com.dkmo.living_chatting;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("/login.html");
    registry.addViewController("/register").setViewName("/cadastro.html");
    registry.addViewController("/notifications")
      .setViewName("/notifications.html"); 
    registry.addViewController("/contacts")
      .setViewName("/list-contacts.html");
    registry.addRedirectViewController("/", "/notifications");
  }
@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photos-profiles/**")
                .addResourceLocations("file:uploads/photos-profiles/");
    registry.addResourceHandler("/imagens/**")
      .addResourceLocations("file:imagens/");
    }
}
