package com.zhang.dao;

import com.zhang.pojo.LogRecord;

import java.util.List;

public interface LogRecordMapper {
    void insertLog(LogRecord logRecord);

    List<LogRecord> getLogRecord();
}
