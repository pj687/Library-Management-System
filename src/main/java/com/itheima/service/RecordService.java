
package com.itheima.service;

import com.itheima.domain.Record;

import java.util.List;

public interface RecordService {
    Integer addRecord(Record var1);

    List<Record> findRecord(Record var1);
}
