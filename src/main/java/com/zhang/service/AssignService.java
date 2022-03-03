package com.zhang.service;

import com.zhang.pojo.Assign;
import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AssignService {
    List<Assign> getAllAssigned(int curr, int nums);

    int getCountOfAssigned();

    int deleteOneAssign(int id);

    List<Assign> searchAssigned(String driverIdCard, String carLicenseLateNumber);

    List<Driver> getDriverByCondition(int curr,int nums);

    List<Driver> searchDriver(String username,String idCard);

    List<Car> getCarByCondition(int curr, int nums);

    List<Car> searchCar(String carName, String licenseLateNumber);

    int getAllRightDriverCount();

    int getAllRightCarCount();

    int addOneAssign(Assign assign);

    void updateCarState(String licenseLateNumber);

    void updateCarState2(String licenseLateNumber);

    void updateProfit(int id,double profit);
}
