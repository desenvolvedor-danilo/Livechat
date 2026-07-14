package com.dkmo.living_chatting.infrastructure.webmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
  // @Override
  // public void addViewControllers(ViewControllerRegistry registry) {
  // registry.addViewController("/login").setViewName("/login.html");
  // registry.addViewController("/register").setViewName("/cadastro.html");
  // registry.addViewController("/notifications")
  // .setViewName("/notifications.html");
  // registry.addViewController("/contacts")
  // .setViewName("/list-contacts.html");
  // registry.addRedirectViewController("/", "/notifications");
  // }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/photos-profiles/**")
        .addResourceLocations("file:photos-profiles/")
        .setCachePeriod(0);
    registry.addResourceHandler("/imagens/**")
        .addResourceLocations("file:imagens/")
        .setCachePeriod(0);
  }

  @Override
  public void addCorsMappings(CorsRegistry corsRegistry) {
    corsRegistry.addMapping("/**")
        .allowedOrigins("https://speakflowchat.vercel.app", "http://localhost:3000")
        // allowedOriginPatterns("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*").allowCredentials(true);
  }
}
