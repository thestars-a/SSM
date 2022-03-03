package com.zhang.service;

import com.zhang.pojo.Driver;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDriver(int curr,int nums);

    int addOneDriver(Driver driver);

    int getCountOfDriver();

    int deleteCheckDriver(List<Driver> list);

    int deleteOneDriver(int id);

    int updateDriver(Driver driver);

    List<Driver> searchDriver(String username,String idCard);

    String getDriverIdCard(String idCard);
}
