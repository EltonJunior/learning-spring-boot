package com.learningspringboot.repository;

import com.learningspringboot.domain.CarUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarUserRepository extends JpaRepository<CarUser, Long>{
  
  CarUser findByUsername(String username);
}
