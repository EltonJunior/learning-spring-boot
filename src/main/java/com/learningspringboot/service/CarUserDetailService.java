package com.learningspringboot.service;

import java.util.Optional;

import com.learningspringboot.repository.CarUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarUserDetailService implements UserDetailsService{

  private final CarUserRepository carUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    return Optional.ofNullable(carUserRepository.findByUsername(username))
    .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
  }

  
}
