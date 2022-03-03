package com.zhang.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRecord {
    private int id;
    private String username;
    private String optIp;
    private String operation;
    private String content;
    //如此传回前端格式不变
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
}
