package com.learningspringboot.service;

import java.util.List;

import com.learningspringboot.domain.Car;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
   * provide the list of vehicle to test the application.
   */
  private List<Car> car = List.of(
    new Car(1L,"Volkswagen","White",220),
    new Car(2L,"Fiat","Red",135),
    new Car(3L,"Renault","Blue",200)
  );

  /**
   * 
   * @apiNote the method listAll() never will be placed on the app
   * this method will always run in background.
   * 
   * @return
   */
  public List<Car> listAll(){
    //private final carService CarService;
    return car;
  }

  /**
   * @apiNote the method findById is used to search the element in the list,
   * as convection, every time when it is request a search and it has an error
   * it return a 404 NOT_FOUND, but in this return it doesn't have information
   * about, here it has the error 400 BAD_REQUEST with the description of ERROR
   * 
   * @param id
   * 
   * @return element with correspondent ID
   */
  public Car findById(long id){
    //private final carService CarService;
    return car.stream()
    .filter(car -> car.getId().equals(id))
    .findFirst()
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car not found"));
  }
  
}
