package com.learningspringboot.mapper;

import com.learningspringboot.domain.Car;
import com.learningspringboot.requests.CarPostRequestBody;
import com.learningspringboot.requests.CarPutRequestBody;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * This Component will organize the Request,
 */
@Mapper(componentModel = "spring")
public abstract class CarMapper {

  /**
   * this is the instance of CarMapper 
   */
  public static final CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

  /**
   * those methods will automatic convert all attribute with has
   * the same name to class CAR
   * @param carPostRequestBody
   * @return
   */
  public abstract Car toCar(CarPostRequestBody carPostRequestBody);
  public abstract Car toCar(CarPutRequestBody carPutRequestBody);
}
