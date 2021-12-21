package com.learningspringboot.configurer;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CarWebMvcConfigurer implements WebMvcConfigurer{

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    
    PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();

    pageHandler.setFallbackPageable(PageRequest.of(0, 5));
    resolvers.add(pageHandler);
  }
  
}

/**
 * The injector @Configuration means to Spring that this class has the 
 * scope Global
 * 
 * This Method will return only 5 object in which search, but if the user 
 * put value in the search it will return the the value put in.
 * 
 * http://localhost:8080/cars -> return 5 itens 
 * 
 * 
 * http://localhost:8080/cars?size=10 -> return 10 itens
 * 
 */
