package com.itheima.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {
    @RequestMapping("/upload")
    @ResponseBody
    public String fileUpload(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {// 保存上传的文件，filepath为保存的目标目录

            String filePath = "D:/"+file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return "uploadSuccess";
        }
        return "uploadFailure";
    }


    @RequestMapping("/download")
    @ResponseBody
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename) throws Exception{
        String path = "D:";// 下载文件所在路径
        File file = new File(path+File.separator+filename); // 创建文件对象
        HttpHeaders headers = new HttpHeaders(); // 设置消息头
        headers.setContentDispositionFormData("attachment", filename);// 打开文件
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 下载返回的文件数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK); // 使用ResponseEntity对象封装返回下载数据
    }

}