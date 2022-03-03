package com.zhang.service.impl;

import com.zhang.dao.CarMapper;
import com.zhang.pojo.Assign;
import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;
import com.zhang.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public int addOneCar(Car car) {
        return carMapper.addOneCar(car);
    }

    @Override
    public List<Car> getAllCar(int curr, int nums) {
        curr=(curr-1)*nums;
        return carMapper.getAllCar(curr,nums);
    }

    @Override
    public int getCountOfCar() {
        return carMapper.getCountOfCar();
    }

    @Override
    public int deleteCheckCar(List<Car> list) {
        for (Car car : list) {
            Assign assigned = carMapper.isAssigned(car);
            if (assigned!=null){
                return -1;
            }
        }
        return carMapper.deleteCheckCar(list);
    }

    @Override
    public int deleteOneCar(int id) {
        Car oneCarById = carMapper.getOneCarById(id);
        Assign assigned = carMapper.isAssigned(oneCarById);
        if (assigned!=null){
            return -1;
        }
        return carMapper.deleteOneCar(id);
    }

    @Override
    public int updateCar(Car car) {
        return carMapper.updateCar(car);
    }

    @Override
    public List<Car> searchCar(String carName, String licenseLateNumber) {
        return carMapper.searchCar(carName, licenseLateNumber);
    }

    @Override
    public String getCarLicenseLateNumber(String licenseLateNumber) {
        return carMapper.getCarLicenseLateNumber(licenseLateNumber);
    }
}
