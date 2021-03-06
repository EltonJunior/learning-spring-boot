package com.learningspringboot.controller;

import java.util.List;

import javax.validation.Valid;

import com.learningspringboot.domain.Car;
import com.learningspringboot.requests.CarPostRequestBody;
import com.learningspringboot.requests.CarPutRequestBody;
import com.learningspringboot.service.CarService;

import org.hibernate.mapping.Any;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
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

  /**
   * 
   * One feature very helpful in the spring is the pagination, to use it
   * only need to create a method type Page and put the argument as Pageable
   * 
   * to search in the browser use http://localhost:8080/cars?size=5
   * this will return only 5 element by page and if it wanna to go to next page
   * 
   * http://localhost:8080/cars?size=5&page=2
   * 
   * other function when the Pageable is included is the sort
   * 
   * http://localhost:8080/cars?size=10&sort=name,desc
   * 
   * this request will return the sort by name with descendent organization.
   * 
   * http://localhost:8080/cars?size=10&sort=name,asc
   * 
   * this request will return the sort by name with ascendent organization.
   *  
   * 
   * @return
   */
  @GetMapping()
  public ResponseEntity<Page<Car>> list(Pageable pageable){
    log.info("The GetMapping is works!");
    return ResponseEntity.ok(carService.listAll(pageable)) ;
  }

  /**
   * this function find item by pass the ID
   * @param id
   * @return
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<Car> findById(@PathVariable long id){
    log.info("The GetMapping is works!");
    return ResponseEntity.ok(carService.findByIdOrThrowBadRequestException(id)) ;
  }
  /**
   * this function find item by pass the ID
   * this function also get the information of user on its REQUEST
   * @param id
   * @return
   */
  @GetMapping(path = "/by-id/{id}")
  public ResponseEntity<Car> findByIdAuthenticationPrincipal(
    @PathVariable long id,
    @AuthenticationPrincipal UserDetails userDetails
    ){
      log.info(userDetails);
      return ResponseEntity.ok(carService.findByIdOrThrowBadRequestException(id)) ;
  }

  /**
   * this function find item by pass the name
   * http://localhost:8080/cars/name?name=String
   * @param name
   * @return
   */
  @GetMapping(path = "/name")
  public ResponseEntity<List<Car>> findByName(@RequestParam String name){
    log.info("The GetMapping by name is works!");

    return ResponseEntity.ok(carService.findByName(name)) ;
  }
  
  /**
   * this function find item by pass the color
   * http://localhost:8080/cars/color?color=String
   * @param color
   * @return
   */
  @GetMapping(path = "/color")
  public ResponseEntity<List<Car>> findByColor(@RequestParam String color){
    log.info("The GetMapping by color is works!");

    return ResponseEntity.ok(carService.findByColor(color)) ;
  }

  /**
   * this function find item by pass the power
   * http://localhost:8080/cars/power?power=Integer
   * @param power
   * @return
   */
  @GetMapping(path = "/power")
  public ResponseEntity<List<Car>> findByPower(@RequestParam Integer power){
    log.info("The GetMapping by power is works!");

    return ResponseEntity.ok(carService.findByPower(power)) ;
  }

  /**
   * The @PostMapping Method is one of Method considering critical because its
   * make modification on the database, it necessary to use some ROLES to 
   * make sure that the user that was REQUEST this Method has sure What is
   * doing. In Spring-boot it uses @PreAuthorize to manage the ROLES.
   * 
   * When it use the @NotEmpty and @NotNull in the function it need to add
   * the directive @Valid, the spring will handle the exception automatic.
   * 
   * @param carPostRequestBody
   * @return
   */
  @PostMapping()
  public ResponseEntity<Car> save(@RequestBody @Valid CarPostRequestBody carPostRequestBody){
    return new ResponseEntity<>( carService.save(carPostRequestBody), HttpStatus.CREATED);
  }

  /**
   * 
   * @param id
   * @return
   */
  @DeleteMapping(path = "/admin/{id}")
  public ResponseEntity<Car> delete(@PathVariable long id){
    carService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * 
   * @param carPutRequestBody
   * @return
   */
  @PutMapping()
  public ResponseEntity<Car> replace(@RequestBody CarPutRequestBody carPutRequestBody){
    carService.replace(carPutRequestBody);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
 * As a @GetMapping when it return only one item, it receive a "/{id}", the @DeleteMapping
 * works as well.
 * 
 * One analogic that it can be done is when it does a @PutMapping, it is change all interred 
 * object, it is used a function replace()
 * 
 * 
 */