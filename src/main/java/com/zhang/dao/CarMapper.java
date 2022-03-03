package com.zhang.dao;

import com.zhang.pojo.Assign;
import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    
    List<Car> getAllCar(@Param("curr") int curr, @Param("nums") int nums);

    int addOneCar(Car car);

    int getCountOfCar();

    int deleteCheckCar(@Param("list") List<Car> list);

    int deleteOneCar(@Param("id") int id);

    int updateCar(Car Car);

    List<Car> searchCar(@Param("carName") String carName,@Param("licenseLateNumber") String licenseLateNumber);

    String getCarLicenseLateNumber(@Param("licenseLateNumber") String licenseLateNumber);

    Assign isAssigned(Car car);

    Car getOneCarById(@Param("id") int id);
}
