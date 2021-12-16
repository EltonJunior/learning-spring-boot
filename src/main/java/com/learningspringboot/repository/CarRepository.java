package com.learningspringboot.repository;

import java.util.List;

import com.learningspringboot.domain.Car;

/**
 * This Class is the DataBase Representation
 * 
 * It will have all method related to Database access
 */
public interface CarRepository {
  
  List<Car> listAll();
}
