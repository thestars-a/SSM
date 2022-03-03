package com.zhang.service;

import com.zhang.pojo.LogRecord;

import java.util.List;

public interface LogRecordService{

    void insertLog(LogRecord logRecord);

    List<LogRecord> getLogRecord();
}
