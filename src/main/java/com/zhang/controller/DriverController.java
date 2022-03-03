package com.zhang.controller;


import com.zhang.controller.aop.OperationLogger;
import com.zhang.pojo.Driver;
import com.zhang.service.DriverService;
import com.zhang.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;


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


    /***
     * 获取所有司机信息
     * @param curr
     * @param nums
     * @return
     */
    @PostMapping("/getAll")
    public Map<String, Object> getAllDriver(@RequestParam("curr") int curr,
                                            @RequestParam("nums") int nums){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Driver> allDriver = driverService.getAllDriver(curr, nums);
        int countOfDriver = driverService.getCountOfDriver();
        result.put("code",0);
        result.put("msg","");
        result.put("count",countOfDriver);
        result.put("data",allDriver);
        return result;
    }

    @PostMapping("/saveDriver")
    @OperationLogger(modelName = "添加了",option = "/driver/saveDriver")
    public Map<String,Object> saveDriver(@RequestBody Driver driver){
        Map<String, Object> result = new HashMap<String, Object>();
        String driverIdCard = driverService.getDriverIdCard(driver.getIdCard());
        if (driverIdCard!=null&&driverIdCard!=""){
            result.put("code",-1);
            result.put("msg","身份证号码重复");
        }else{
            int i = driverService.addOneDriver(driver);
            result.put("code",i);
        }
        return result;
    }


    @PostMapping("/deleteCheckDriver")
    @OperationLogger(modelName = "删除了",option = "/driver/deleteCheckDriver")
    public Map<String,Object> deleteCheckDriver(@RequestBody List<Driver> drivers){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = driverService.deleteCheckDriver(drivers);
        result.put("code",i);
        return result;
    }

    @PostMapping("/deleteOneDriver")
    @OperationLogger(modelName = "删除了",option = "/driver/deleteOneDriver")
    public Map<String,Object> deleteOneDriver(@RequestBody Driver driver){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = driverService.deleteOneDriver(driver.getId());
        result.put("code",i);
        return result;
    }

    @PostMapping("/updateDriver")
    @OperationLogger(modelName = "修改了",option = "/driver/updateDriver")
    public Map<String,Object> updateDriver(@RequestBody Driver driver){
        Map<String, Object> result = new HashMap<String, Object>();
        int i = driverService.updateDriver(driver);
        result.put("code",i);
        return result;
    }

    @PostMapping("/searchDriver")
    public Map<String,Object> searchDriver(@RequestParam("username") String username,@RequestParam("idCard") String idCard){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Driver> drivers = driverService.searchDriver(username, idCard);
        result.put("code",0);
        result.put("msg","");
        result.put("data",drivers);
        return result;
    }
}
