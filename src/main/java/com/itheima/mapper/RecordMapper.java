package com.itheima.mapper;

import com.itheima.domain.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {

    @Insert("<script>  " +
            "insert into record(record_bookisbn,record_bookname,record_borrower,record_borrowtime,record_remandtime)   " +
            "  values <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">  " +
            "  #{record_bookisbn},#{record_bookname},#{record_borrower},#{record_borrowtime},#{remandtime}, "+
            "  </trim>"+
            "</script>  ")
    Integer insertRecord(Record book);


    @Select("select * from record where record_borrower like CONCAT('%',#{record_borrower},'%') and record_bookname like CONCAT('%',#{record_bookname},'%') ")
    @Results(
            @Result(column = "record_remandtime", property = "remandtime")
    )
    List<Record> findRecord(Record record);
}
