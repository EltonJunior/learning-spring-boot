package com.learningspringboot.service;

import java.util.List;

import com.learningspringboot.domain.Car;

import org.springframework.stereotype.Service;

/**
 * This class is responsible to keep the rules "Business logic" to the application
 * 
 * The injectable @Service is responsible to became the class CarService as a Service
 * it is called Bean and according (www.baeldung.com/spring-bean), In Spring, the objects 
 * that form the backbone of your application and that are managed by the Spring IoC 
 * container are called beans. A bean is an object that is instantiated, assembled, 
 * and otherwise managed by a Spring IoC container
 */
@Service
//@AllArgsConstructor /* This injectable will create a constructor*/
public class CarService {

  /**
   * 
   * @apiNote the method listAll() never will be placed on the app
   * this method will always run in background.
   * 
   * @return
   */
  public List<Car> listAll(){
    //private final carService CarService;
    return List.of(
      new Car(1L,"Volkswagen","White",220),
      new Car(2L,"Fiat","Red",135),
      new Car(3L,"Renault","Blue",200)
    );
  }
  
}
