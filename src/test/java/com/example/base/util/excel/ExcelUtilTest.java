package com.example.base.util.excel;

import com.example.SpringBootHelloApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;



@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringBootHelloApplication.class)
public class ExcelUtilTest {

    @Test
    public void readExcelTest() throws Exception {
        ExcelUtil.saxReadListStringV2007("E:\\登陆客户1.xlsx");
    }

}