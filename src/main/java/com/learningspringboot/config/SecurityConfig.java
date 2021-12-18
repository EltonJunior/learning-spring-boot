package com.learningspringboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import lombok.extern.log4j.Log4j2;

@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * this Method will content the password, as good practical its will
   * have the strong password, use PasswordEncoder
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder passwordEncoder =  PasswordEncoderFactories.createDelegatingPasswordEncoder();
    log.info("PasswordEncoder: {}", passwordEncoder.encode("test"));
    auth.inMemoryAuthentication()
    .withUser("USERNAME")
    .password(passwordEncoder.encode("PASSWORD"))
    .roles("ROLES");
  }

  /**
   * all HttpRequest will be passed in this Method and will be available
   * in the basic authentication
   * 
   * the use of withHttpOnlyFalse() will force the Front-end has the same Cookie
   * when it was perform the @PostRequest it shall pass the Cookie also.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    .and()
    .authorizeRequests()
    .anyRequest()
    .authenticated()
    .and()
    .httpBasic();
  }
  
}

/**
 * The Spring as a patter has a lot of security, only of to
 * install the dependence spring-boot-starter-security
 * when the application is running it is necessary to enter 
 * with user "user" and password "The number generated in the Terminal"
 * so, this class will make the interface to it.
 * 
 * the first inject is @EnableWebSecurity, as default this inject is a 
 * Spring bean and will run in the start of application.
 * 
 * it is working with Bean and to spring recognize it is necessary to 
 * extend the class to WebSecurityConfigurerAdapter
 * 
 * in the configure(HttpSecurity http) it will be configured with
 * the security.
 * 
 * After the implementation of those method the spring no longer will 
 * show the password and it will use the new one.
 * 
 */