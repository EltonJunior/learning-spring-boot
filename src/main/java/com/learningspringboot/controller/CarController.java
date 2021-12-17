package com.learningspringboot.controller;

import java.util.List;

import com.learningspringboot.domain.Car;
import com.learningspringboot.service.CarService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  public ResponseEntity<List<Car>> list(){
    log.info("The GetMapping is works!");
    return ResponseEntity.ok(carService.listAll()) ;
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Car> findById(@PathVariable long id){
    log.info("The GetMapping is works!");
    return ResponseEntity.ok(carService.findById(id)) ;
  }

  @PostMapping()
  public ResponseEntity<Car> save(@RequestBody Car car){
    return new ResponseEntity<>( carService.save(car), HttpStatus.CREATED);
  }

}


/**
 * As a good practical, this method get implemented below, 
 * return information as a Json list only. 
 * @GetMapping()
 * public List<Car> list(){
 *   log.info("The GetMapping is works!");
 *   return carService.listAll();
 * }
 * We should return together the status of request, to it, it uses 
 * ResponseEntity. 
 * 
 * Is there 2 way to do it so.
 * 
 * 1 - 
 * @GetMapping()
 * public ResponseEntity<List<Car>> list(){
 *   log.info("The GetMapping is works!");
 *   return new ResponseEntity<>(carService.listAll(),HttpStatus.OK) ;
 * }
 * 
 * 2 - 
 *  * @GetMapping()
 * public ResponseEntity<List<Car>> list(){
 *   log.info("The GetMapping is works!");
 *   return ResponseEntity.ok(carService.listAll()) ;
 * }
 * 
 * 
 * when it was made a Request, if it has more than one @GetMapping() without 
 * specification, like "/{id}", it will return a error when compile. this behavior 
 * is the same on @PostMapping.  
 * 
 * On the Method @PostMapping, it is necessary pass as argument of request the BODY,
 * to do that it uses @RequestBody with the type of Method
 *  
 * 
 */