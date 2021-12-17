package com.learningspringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.learningspringboot.domain.Car;
import com.learningspringboot.repository.CarRepository;
import com.learningspringboot.requests.CarPostRequestBody;
import com.learningspringboot.requests.CarPutRequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor /* This injectable will create a constructor*/
public class CarService {

  private final CarRepository carRepository;

  /**
   * provide the list of vehicle to test the application.
   * 
   * to make change on the list, it was change to static.
   *
   *  private static List<Car> cars; 
   *
   *  static{
   *     cars = new ArrayList<>(List.of(
   *     new Car(1L,"Volkswagen","White",220),
   *     new Car(2L,"Fiat","Red",135),
   *     new Car(3L,"Renault","Blue",200)
   *   ));
   * }
   * /

  /**
   * 
   * @apiNote the method listAll() never will be placed on the app
   * this method will always run in background.
   * 
   * @return
   */
  public List<Car> listAll(){
    /**
     * return cars;
     * Now, instead of return only the object, it will find the 
     * object inside of DB 
     */
    return carRepository.findAll(); 
  }

  /**
   * @apiNote the method findByIdOrThrowBadRequestException is used to search the element in the list,
   * as convection, every time when it is request a search and it has an error
   * it return a 404 NOT_FOUND, but in this return it doesn't have information
   * about, here it has the error 400 BAD_REQUEST with the description of ERROR
   * 
   * @param id
   * 
   * @return element with correspondent ID
   */
  public Car findByIdOrThrowBadRequestException(long id){
  /*
   * return cars.stream()
   * .filter(car -> car.getId().equals(id))
   * .findFirst()
   * .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car not found"));
   * 
   * Now, instead of this find by id it will use the findByIdOrThrowBadRequestException from CRUDRepository, its return 
   * an Optional and if it doesn't find anything it return the BAD_REQUEST
   */ 
    return carRepository
      .findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car not found"));
  }

  /**
   * @apiNote to test the application, it was implemented the generate id, as database does.
   * car.setId(ThreadLocalRandom.current().nextLong(3,10000)); this function generated ids 
   * from 3 to 100000
   * @param car
   * @return return a create item.
   */
  public Car save(CarPostRequestBody carPostRequestBody) {
   /**
    * car.setId(ThreadLocalRandom.current().nextLong(3,10000));
    * cars.add(car);
    * return car;
    * 
    * now with the CarPostRequestBody as a DTO class, our method needs a mapping method, 
    * so, it was need inject the @Builder in the Car.java to construct the method
    */

    return carRepository.save(Car.builder().name(carPostRequestBody.getName()).build());
  }

  /**
   * When it receive a DELETE request, it uses findByIdOrThrowBadRequestException to search the object,
   * if object is found the function remove will remove it.
   * if it was request the same object, the findByIdOrThrowBadRequestException will search, and if it was not
   * find it return a BAD_REQUEST. 
   * 
   * @param id
   */
  public void delete(long id) {
    /* cars.remove(findByIdOrThrowBadRequestException(id)); */
    carRepository.delete(findByIdOrThrowBadRequestException(id));
  }

  public void replace(CarPutRequestBody carPutRequestBody) {
    /** 
     * delete(car.getId());
     * cars.add(car);
     */
    
    Car savedCar = findByIdOrThrowBadRequestException(carPutRequestBody.getId());
    Car car = Car.builder()
    .id(savedCar.getId())
    .name(carPutRequestBody.getName())
    .build();

    carRepository.save(car);
    
  }
  
  
}


/**
 * After implement the DB, this class can be REFACTORED by remove the 
 * List created to test its REQUEST Methods, but instead of remove it will 
 * commented.
 * 
 * 
 */