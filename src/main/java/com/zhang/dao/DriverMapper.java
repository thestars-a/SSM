package com.zhang.dao;

import com.zhang.pojo.Assign;
import com.zhang.pojo.Driver;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DriverMapper {
    List<Driver> getAllDriver(@Param("curr") int curr,@Param("nums") int nums);

    int addOneDriver(Driver driver);

    int getCountOfDriver();

    int deleteCheckDriver(@Param("list") List<Driver> list);

    int deleteOneDriver(@Param("id") int id);

    int updateDriver(Driver driver);

    List<Driver> searchDriver(@Param("username") String username,@Param("idCard") String idCard);

    String getDriverIdCard(@Param("idCard") String idCard);

    Assign isAssigned(Driver driver);

    Driver getOneDriverById(@Param("id") int id);
}
