package com.zhang.controller;

import com.zhang.pojo.LogRecord;
import com.zhang.service.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logRecord")
public class LogRecordController {

    @Autowired
    private LogRecordService logRecordService;

    @PostMapping("/getLogRecord")
    public Map<String,Object> getLogRecord(){
        Map<String,Object> result = new HashMap<String, Object>();
        List<LogRecord> logRecords = logRecordService.getLogRecord();
        result.put("data",logRecords);
        return result;
    }
}
