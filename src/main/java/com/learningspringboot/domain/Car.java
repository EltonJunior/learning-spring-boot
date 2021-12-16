package com.learningspringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor /* This injectable will create a constructor*/
public class Car {

  private Long id;
  private String name;
  private String color;
  private Integer power;

/**
 * 
 * This class represent with have in the Database
 * 
 * This project is using Lombok to generate the method and constructors for the app
 * in the moment of compiler
 * 
 * @Data - responsible to generated the Getters, Setters, equal and hashcode 
 * @AllArgsConstructor - responsible to generated the constructor
 * 
 */
  
}
