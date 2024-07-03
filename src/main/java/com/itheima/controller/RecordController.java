package com.itheima.controller;

import com.itheima.domain.Record;
import com.itheima.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {

    //
    @Autowired
    private RecordService recordService;

    //查询借阅记录功能
    @RequestMapping("/searchRecords")
    public String searchRecords(Model model,Record recordSearch){
        List<Record> recordList = recordService.findRecord(recordSearch);

        model.addAttribute("recordSearch", recordSearch);
        model.addAttribute("total", recordList.size());
        model.addAttribute("rows", recordList);
        model.addAttribute("pageNum", recordList);
        model.addAttribute("gourl", recordList);
        return "record";
    }
}
