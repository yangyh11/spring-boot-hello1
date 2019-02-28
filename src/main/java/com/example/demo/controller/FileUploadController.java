package com.example.demo.controller;

import com.example.base.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * @description: 文件上传
 * @author: yangyh
 * @create: 2019-02-27 20:34
 **/
@Controller
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("/file")
    public String file() {
        return "/file";
    }

    /**
     * 文件上传
     **/
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        //文件存放路径
        String filePath = "d:/files/";
        String result = FileUtil.fileUpload(filePath, file);
        return result;
    }
}
