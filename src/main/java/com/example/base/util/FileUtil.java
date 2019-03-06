package com.example.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description: 文件操作工具类
 * @author: yangyh
 * @create: 2019-02-28 13:38
 **/
@Component
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static String fileUpload(String filePath, MultipartFile file){

        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        //查看路径是否存在，不存在就创建
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败";
    }
}
