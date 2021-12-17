package com.learningspringboot.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor /* This injectable will create a constructor*/
public class Car {

  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("color")
  private String color;

  @JsonProperty("power")
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
 * On of the characteristic of JSON is that, if the name of the objet that is change 
 * be different from the of the database, it won't make the change. one thing that it 
 * used to fix it, is to use the @JsonProperty("name_of_component").
 * 
 * ex: 
 * @JsonProperty("name")
 * private String name_of_car;
 * 
 * it means that when it was request a name_of_car object it necessary only pass the "name" 
 * as a parameter. 
 * 
 */
  
}
