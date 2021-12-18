package com.learningspringboot.repository;

import java.util.List;

import com.learningspringboot.domain.Car;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This Class is the DataBase Representation
 * 
 * It will have all method related to Database access
 */
public interface CarRepository extends JpaRepository<Car, Long>{
  
  List<Car> findByName(String name);
  List<Car> findByColor(String color);
  List<Car> findByPower(Integer power);
}


/**
 * Now, it has a DB-Connection, this Interface could be refactored to
 * communication to DB.
 * 
 * it will extend the JpaRepository the parameter that will pass to is
 * the Class that represent the repository, in this project it will be the Car,
 * and the attribute that represent the ID. the type of ID. 
 * 
 * In order to make the connection to DB, the springs has some methods that will 
 * create a selects based on method that will be called.
 * 
 * One big advantage to use JpaRepository is it doesn't need to create any method.
 * All method that is needed should be find inside the JpaRepository 
 * 
 * When it created a functions findBy${param} the spring will take care of it.
 */