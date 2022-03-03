package com.zhang.service.impl;

import com.zhang.dao.LogRecordMapper;
import com.zhang.pojo.LogRecord;
import com.zhang.service.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogRecordServiceImpl implements LogRecordService {

    @Autowired
    private LogRecordMapper logRecordMapper;

    @Override
    public void insertLog(LogRecord logRecord) {
        logRecordMapper.insertLog(logRecord);
    }

    @Override
    public List<LogRecord> getLogRecord() {
        return logRecordMapper.getLogRecord();
    }
}
