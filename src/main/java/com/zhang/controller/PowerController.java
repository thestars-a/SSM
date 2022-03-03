package com.zhang.controller;

import com.zhang.pojo.Admin;
import com.zhang.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/power")
public class PowerController {

    @Autowired
    private PowerService powerService;


    @PostMapping("/getAllAdmin")
    public Map<String,Object> getAll(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<Admin> all = powerService.getAllAdmin();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getUsername().equals("admin")){
                all.remove(i);
            }
        }
        result.put("code",0);
        result.put("msg","");
        result.put("data",all);
        return result;
    }


    @PostMapping("/updatePermissions")
    public Map<String,Object> updatePermissions(@RequestBody Admin admin){
        Map<String,Object> result = new HashMap<String, Object>();
        int i = powerService.updatePermissions(admin);
        if (i==1){
            result.put("code",0);
        }else{
            result.put("code",1);
        }
        return result;
    }
}
