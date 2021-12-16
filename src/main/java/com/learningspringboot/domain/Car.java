package com.learningspringboot.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor /* This injectable will create a constructor*/
public class Car {

  private String name;
  private String color;
  private Integer power;

  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public Integer getPower() {
    return power;
  }
  public void setPower(Integer power) {
    this.power = power;
  }
  
}
