package com.zhang.service;

import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;

import java.util.List;

public interface CarService {
    int addOneCar(Car car);
    
    List<Car> getAllCar(int curr, int nums);

    int getCountOfCar();

    int deleteCheckCar(List<Car> list);

    int deleteOneCar(int id);

    int updateCar(Car car);

    List<Car> searchCar(String carName,String licenseLateNumber);

    String getCarLicenseLateNumber(String licenseLateNumber);
}
