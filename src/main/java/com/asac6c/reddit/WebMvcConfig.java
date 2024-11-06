package com.asac6c.reddit;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("http://namucy-s3-v1.s3-website.ap-northeast-2.amazonaws.com")
        .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
  }
}
