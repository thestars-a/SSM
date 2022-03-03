package com.zhang.controller;

import com.zhang.controller.aop.OperationLogger;
import com.zhang.pojo.Assign;
import com.zhang.pojo.Car;
import com.zhang.pojo.Driver;
import com.zhang.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assign")
public class AssignController {

    @Autowired
    private AssignService assignService;

    @PostMapping("/getAllAssigned")
    public Map<String,Object> getAllAssigned(@RequestParam("curr") int curr,
                                     @RequestParam("nums") int nums){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Assign> assigneds = assignService.getAllAssigned(curr, nums);
        System.out.println(assigneds);
        int countOfAssigned = assignService.getCountOfAssigned();
        result.put("code",0);
        result.put("msg","");
        result.put("count",countOfAssigned);
        result.put("data",assigneds);
        return result;
    }

    @PostMapping("/deleteOneAssign")
    @OperationLogger(modelName = "删除了",option = "/assign/deleteOneAssign")
    public Map<String,Object> deleteOneAssign(@RequestBody Assign assign){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = assignService.deleteOneAssign(assign.getId());
        assignService.updateCarState2(assign.getCarLicenseLateNumber());
        result.put("code",i);
        return result;
    }

    @PostMapping("/searchAssigned")
    public Map<String,Object> searchAssigned(@RequestParam("driverIdCard") String driverIdCard,@RequestParam("carLicenseLateNumber") String carLicenseLateNumber){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Assign> assigns = assignService.searchAssigned(driverIdCard, carLicenseLateNumber);
        result.put("code",0);
        result.put("msg","");
        result.put("data",assigns);
        return result;
    }

    @PostMapping("/getDriverByCondition")
    public Map<String, Object> getAllDriver(@RequestParam Map<String, Object> paramMap){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Driver> allDriver = assignService.getDriverByCondition(Integer.parseUnsignedInt(paramMap.get("curr").toString()), Integer.parseUnsignedInt(paramMap.get("nums").toString()));
        int countOfDriver = assignService.getAllRightDriverCount();
        if (( paramMap.get("username")!=null &&  paramMap.get("username")!="")
                ||( paramMap.get("idCard")!=null &&  paramMap.get("idCard")!="")){
            String username= (String) paramMap.get("username");
            String idCard = (String) paramMap.get("idCard");
            List<Driver> drivers = assignService.searchDriver(username, idCard);
            countOfDriver = drivers.size();
            result.put("code",0);
            result.put("msg","");
            result.put("count",countOfDriver);
            result.put("data",drivers);
            return result;
        }
        result.put("code",0);
        result.put("msg","");
        result.put("count",countOfDriver);
        result.put("data",allDriver);
        return result;
    }
    @PostMapping("/getCarByCondition")
    public Map<String, Object> getAllCar(@RequestParam Map<String, Object> paramMap){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Car> carList = assignService.getCarByCondition(Integer.parseUnsignedInt(paramMap.get("curr").toString()), Integer.parseUnsignedInt(paramMap.get("nums").toString()));
        int size = assignService.getAllRightCarCount();
        if (( paramMap.get("carName")!=null &&  paramMap.get("carName")!="")
                ||( paramMap.get("licenseLateNumber")!=null &&  paramMap.get("licenseLateNumber")!="")){
            String carName= (String) paramMap.get("carName");
            String licenseLateNumber = (String) paramMap.get("licenseLateNumber");
            List<Car> cars = assignService.searchCar(carName,licenseLateNumber);
            size = cars.size();
            result.put("code",0);
            result.put("msg","");
            result.put("count",size);
            result.put("data",cars);
            return result;
        }
        result.put("code",0);
        result.put("msg","");
        result.put("count",size);
        result.put("data",carList);
        return result;
    }

    @PostMapping("/saveAssign")
    @OperationLogger(modelName = "添加了",option = "/assign/saveAssign")
    public Map<String,Object> saveCar(@RequestBody Assign assign){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = assignService.addOneAssign(assign);
        assignService.updateCarState(assign.getCarLicenseLateNumber());
        if (i<=0){
            i=-1;
        }
        result.put("code",i);
        return result;
    }

    @PostMapping("updateProfit")
    public Map<String,Object> updateProfit(@RequestParam("id") int id,@RequestParam("profit") double profit){
        Map<String,Object> result = new HashMap<>();
        assignService.updateProfit(id,profit);
        return result;
    }
}
