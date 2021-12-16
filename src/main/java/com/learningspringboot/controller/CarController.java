package com.learningspringboot.controller;

import java.util.List;

import com.learningspringboot.domain.Car;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("cars")/* by patter the name of Request Method is on plural*/
@Log4j2
@AllArgsConstructor /* This injectable will create a constructor*/
public class CarController {

  @GetMapping(value="list")
  private List<Car> list(){
    return List.of(
      new Car("VolksWagem","White",220),
      new Car("Fiat","Red",135),
      new Car("Renault","Blue",200)
      );
  }
}
