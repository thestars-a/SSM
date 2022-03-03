package com.zhang.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private int id;
    private String username;
    private String sex;
    private int age;
    private String licenseAndAge;
    private String idCard;
    @JSONField(name = "file")
    private String driverPicPath;
}
