package com.zhang.service.impl;

import com.zhang.dao.AssignMapper;
import com.zhang.pojo.Assign;
import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;
import com.zhang.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignServiceImpl implements AssignService {

    @Autowired
    private AssignMapper assignMapper;

    @Override
    public List<Assign> getAllAssigned(int curr, int nums) {
        curr=(curr-1)*nums;
        return assignMapper.getAllAssigned(curr, nums);
    }

    @Override
    public int getCountOfAssigned() {
        return assignMapper.getCountOfAssigned();
    }

    @Override
    public int deleteOneAssign(int id) {
        return assignMapper.deleteOneAssign(id);
    }

    @Override
    public List<Assign> searchAssigned(String driverIdCard, String carLicenseLateNumber) {
        return assignMapper.searchAssigned(driverIdCard, carLicenseLateNumber);
    }

    @Override
    public List<Driver> getDriverByCondition(int curr, int nums) {
        curr=(curr-1)*nums;
        List<Assign> allAssignedNoCondition = assignMapper.getAllAssignedNoCondition();
        if (allAssignedNoCondition.size()==0){
            allAssignedNoCondition.add(new Assign(-1,"-1","-1","-1","-1",0.0));
        }
        return assignMapper.getDriverByCondition(curr,nums,allAssignedNoCondition);
    }

    @Override
    public List<Driver> searchDriver(String username, String idCard) {
        return assignMapper.searchDriver(username, idCard);
    }

    @Override
    public List<Car> getCarByCondition(int curr, int nums) {
        curr=(curr-1)*nums;
        return assignMapper.getCarByCondition(curr, nums);
    }

    @Override
    public List<Car> searchCar(String carName, String licenseLateNumber) {
        return assignMapper.searchCar(carName, licenseLateNumber);
    }

    @Override
    public int getAllRightDriverCount() {
        List<Assign> list = assignMapper.getAllAssignedNoCondition();
        if (list.size()==0){
            list.add(new Assign(-1,"-1","-1","-1","-1",0.0));
        }
        return assignMapper.getAllRightDriverCount(list);
    }

    @Override
    public int getAllRightCarCount() {
        return assignMapper.getAllRightCarCount();
    }

    @Override
    public int addOneAssign(Assign assign) {
        return assignMapper.addOneAssign(assign);
    }

    @Override
    public void updateCarState(String licenseLateNumber) {
        assignMapper.updateCarState(licenseLateNumber);
    }

    @Override
    public void updateCarState2(String licenseLateNumber) {
        assignMapper.updateCarState2(licenseLateNumber);
    }

    @Override
    public void updateProfit(int id, double profit) {
        assignMapper.updateProfit(id,profit);
    }
}
