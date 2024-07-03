package com.itheima.service.impl;


import com.itheima.domain.Record;
import com.itheima.mapper.RecordMapper;
import com.itheima.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Integer addRecord(Record record) {
        return recordMapper.insertRecord(record);
    }

    @Override
    public List<Record> findRecord(Record record) {
        return recordMapper.findRecord(record);
    }


}
