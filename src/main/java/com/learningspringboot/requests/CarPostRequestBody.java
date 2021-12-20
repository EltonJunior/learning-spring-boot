package com.learningspringboot.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CarPostRequestBody {
  
  @NotEmpty(message = "The name cannot be Empty")
  private String name;

  @NotEmpty(message = "The name color be Empty")
  private String color;

  @NotNull(message = "The name power be Null")
  private Integer power;
}

/**
 * when the domains are of the entity type, as we have in control,
 *  as a good practice, we cannot use the classes that represent the database.
 *  as persistence as the REQUESTEBODY value in the control.
 *  that's why this class was created, this class is a DTO standard in the Dev community.
 * 
 *  in the post application, if you leave it the way it is, you will need to pass the ID
 *  as a null argument, one of the advantages of working with a standard DTO class,
 *  and that we can use Lombok to manage this for us.
 * 
 *  this way of using the application, helps to increase cohesion, of course, now it will
 *  need a class for each request, but it will help in a possible factorization
 *  of the code. 
 * 
 *  One thing is important to ensure is that the content on the variable is empty or null 
 * before write on Database, for this the Spring boot has the Annotation  
 * @NotEmpty(message = "The name power be Empty") or 
 * @NotNull(message = "The name power be Null")
 * where @NotEmpty is to String and @NotNull to Integer 
 * instead of put in the Database those verification. 
 */