package com.zhang.dao;

import com.zhang.pojo.Assign;
import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AssignMapper {

    List<Assign> getAllAssigned(@Param("curr") int curr, @Param("nums") int nums);


    List<Assign> getAllAssignedNoCondition();


    int getCountOfAssigned();

    int deleteOneAssign(@Param("id") int id);

    List<Assign> searchAssigned(@Param("driverIdCard") String driverIdCard,@Param("carLicenseLateNumber") String carLicenseLateNumber);

    List<Driver> getDriverByCondition(@Param("curr") int curr,@Param("nums") int nums,@Param("list") List<Assign> list);

    List<Driver> searchDriver(@Param("username") String username,@Param("idCard") String idCard);

    List<Car> getCarByCondition(@Param("curr") int curr, @Param("nums") int nums);

    List<Car> searchCar(@Param("carName") String carName,@Param("licenseLateNumber") String licenseLateNumber);

    int getAllRightDriverCount(@Param("list") List<Assign> list);

    int getAllRightCarCount();

    int addOneAssign(Assign assign);

    void updateCarState(@Param("licenseLateNumber") String licenseLateNumber);

    void updateCarState2(@Param("licenseLateNumber") String licenseLateNumber);

    void updateProfit(@Param("id") int id, @Param("profit") double profit);
}
