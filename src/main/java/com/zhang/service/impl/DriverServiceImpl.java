package com.zhang.service.impl;

import com.zhang.dao.DriverMapper;
import com.zhang.pojo.Assign;
import com.zhang.pojo.Driver;
import com.zhang.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverMapper driverMapper;



    @Override
    public List<Driver> getAllDriver(int curr, int nums) {
        curr=(curr-1)*nums;
        return driverMapper.getAllDriver(curr,nums);
    }

    @Override
    public int addOneDriver(Driver driver) {
        return driverMapper.addOneDriver(driver);
    }

    @Override
    public int getCountOfDriver() {
        return driverMapper.getCountOfDriver();
    }

    @Override
    public int deleteCheckDriver(List<Driver> list) {
        for (Driver driver : list) {
            Assign assigned = driverMapper.isAssigned(driver);
            if (assigned!=null){
                return -1;
            }
        }
        return driverMapper.deleteCheckDriver(list);
    }

    @Override
    public int deleteOneDriver(int id) {
        Driver oneDriverById = driverMapper.getOneDriverById(id);
        Assign assigned = driverMapper.isAssigned(oneDriverById);
        if (assigned!=null){
            return -1;
        }
        return driverMapper.deleteOneDriver(id);
    }

    @Override
    public int updateDriver(Driver driver) {
        return driverMapper.updateDriver(driver);
    }

    @Override
    public List<Driver> searchDriver(String username, String idCard) {
        return driverMapper.searchDriver(username, idCard);
    }

    @Override
    public String getDriverIdCard(String idCard) {
        return driverMapper.getDriverIdCard(idCard);
    }
}
