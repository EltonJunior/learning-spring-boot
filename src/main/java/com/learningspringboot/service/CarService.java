package com.learningspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
   * 
   * to make change on the list, it was change to static.
   */
  private static List<Car> cars; 

  static{
      cars = new ArrayList<>(List.of(
      new Car(1L,"Volkswagen","White",220),
      new Car(2L,"Fiat","Red",135),
      new Car(3L,"Renault","Blue",200)
    ));
  }

  /**
   * 
   * @apiNote the method listAll() never will be placed on the app
   * this method will always run in background.
   * 
   * @return
   */
  public List<Car> listAll(){
    //private final carService CarService;
    return cars;
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
    return cars.stream()
    .filter(car -> car.getId().equals(id))
    .findFirst()
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car not found"));
  }

  /**
   * @apiNote to test the application, it was implemented the generate id, as database does.
   * car.setId(ThreadLocalRandom.current().nextLong(3,10000)); this function generated ids 
   * from 3 to 100000
   * @param car
   * @return return a create item.
   */
  public Car save(Car car) {
    car.setId(ThreadLocalRandom.current().nextLong(3,10000));
    cars.add(car);
    return car;
  }
  
}
