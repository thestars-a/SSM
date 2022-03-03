package com.zhang.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private int id;
    private String carName;
    private String carType;
    private String licenseLateNumber;

    @JSONField(name="Text")
    private String carDescribe;

    private String state;

    @JSONField(name="file")
    private String carPicPath;
}
