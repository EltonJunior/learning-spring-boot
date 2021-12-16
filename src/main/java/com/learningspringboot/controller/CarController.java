package com.learningspringboot.controller;

import java.util.List;

import com.learningspringboot.domain.Car;
import com.learningspringboot.service.CarService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * This class is responsible to have all end points
 * GET/SET/POST/DELETE
 */

@RestController
@RequestMapping("cars")/* by patter the name of Request Method is on plural*/
@Log4j2
@AllArgsConstructor /* This injectable will create a constructor*/
public class CarController {

  private final CarService carService;

  @GetMapping()
  public List<Car> list(){
    log.info("The GetMapping is works!");
    return carService.listAll();
  }
}
