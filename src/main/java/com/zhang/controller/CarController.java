package com.zhang.controller;

import com.zhang.controller.aop.OperationLogger;
import com.zhang.pojo.Car;
import com.zhang.service.CarService;
import com.zhang.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    /***
     * 上传图片请求
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Map<String,Object> upload(MultipartFile file, HttpServletRequest request) throws IOException {
        return UploadUtil.uploadPic(file, request);
    }

    @PostMapping("/saveCar")
    @OperationLogger(modelName = "添加了",option = "/car/saveCar")
    public Map<String,Object> saveCar(@RequestBody Car car){
        Map<String, Object> result = new HashMap<String, Object>();
        String driverIdCard = carService.getCarLicenseLateNumber(car.getLicenseLateNumber());
        if (driverIdCard!=null&&driverIdCard!=""){
            result.put("code",-1);
            result.put("msg","车牌号重复");
        }else{
            int i = carService.addOneCar(car);
            result.put("code",i);
        }
        return result;
    }

    /***
     * 获取所有车信息
     * @param curr
     * @param nums
     * @return
     */
    @PostMapping("/getAll")
    public Map<String, Object> getAllCar(@RequestParam("curr") int curr,
                                            @RequestParam("nums") int nums){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Car> allCar = carService.getAllCar(curr, nums);
        int countOfCar = carService.getCountOfCar();
        result.put("code",0);
        result.put("msg","");
        result.put("count",countOfCar);
        result.put("data",allCar);
        return result;
    }

    @PostMapping("/deleteCheckCar")
    @OperationLogger(modelName = "删除了",option = "/car/deleteCheckCar")
    public Map<String,Object> deleteCheckCar(@RequestBody List<Car> cars){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = carService.deleteCheckCar(cars);
        result.put("code",i);
        return result;
    }

    @PostMapping("/deleteOneCar")
    @OperationLogger(modelName = "删除了",option = "/car/deleteOneCar")
    public Map<String,Object> deleteOneCar(@RequestBody Car car){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = carService.deleteOneCar(car.getId());
        result.put("code",i);
        return result;
    }

    @PostMapping("/updateCar")
    @OperationLogger(modelName = "修改了",option = "/car/updateCar")
    public Map<String,Object> updateCar(@RequestBody Car car){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = carService.updateCar(car);
        result.put("code",i);
        return result;
    }

    @PostMapping("/searchCar")
    public Map<String,Object> searchCar(@RequestParam("carName") String carName,
                                        @RequestParam("licenseLateNumber") String licenseLateNumber){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Car> cars = carService.searchCar(carName, licenseLateNumber);
        result.put("code",0);
        result.put("msg","");
        result.put("data",cars);
        return result;
    }
}
