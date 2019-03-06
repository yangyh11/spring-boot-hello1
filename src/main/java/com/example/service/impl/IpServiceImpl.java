package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.base.util.HttpClientUtil;
import com.example.base.util.excel.ExcelUtil;
import com.example.dao.IpInfoDao;
import com.example.entity.IpInfo;
import com.example.service.IpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-04 10:14
 **/
@Service
public class IpServiceImpl implements IpService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static String preifix = "http://opendata.baidu.com/api.php";

    private static String suffix = "query=%s&co=&resource_id=6006&t=1412300361645&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery1102026811896078288555_1412299994977&_=1412299994981";


    @Resource
    private IpInfoDao ipInfoDao;

    @Override
    public void updteLocatio() {

        //从数据库查询
        List<IpInfo> ipInfoList = ipInfoDao.getIpInfo();

        for (IpInfo ipInfo : ipInfoList) {

            String ip = ipInfo.getIp();
            String location = this.getLocation(ip);

            //更新数据库
            ipInfoDao.updateLocation(ipInfo.getId(), location);
            logger.info("更新的id：" + ipInfo.getId() + ",ip:" + ip + ",所属地址是：" + location);
        }
    }

    @Override
    public void updteLocatioByExcel() {

        //读取excel文件
        List<List<String>> dataList = ExcelUtil.saxReadListStringV2007("E:\\登陆客户1.xlsx");

        //调用百度api获取ip所属地址
        for (List ipInfoList : dataList) {
            String location = this.getLocation((String) ipInfoList.get(2));
            if (ipInfoList.size() == 4) {
                ipInfoList.set(3, location);
            } else {
                ipInfoList.add(location);
            }
        }

        //写excel
        ExcelUtil.writeWithoutHead(dataList, null, null, "E:\\登陆客户1.xlsx");
    }


    private String getLocation(String ip) {

        String param = String.format(suffix, ip);

        String url = preifix + "?" + param;

        try {
            byte[] content = HttpClientUtil.httpGet(url, "gbk", null);
            String contentStr = new String(content, "gbk");
            logger.info("服务响应报文{}", contentStr);
            if (StringUtils.isBlank(contentStr)) {
                Map<String, String> busin_param = new HashMap<>();
                logger.info("没有返回任何数据");
            }

            String str = contentStr.substring(47, contentStr.length() - 2);
            JSONObject jsonObject = JSON.parseObject(str);
            JSONArray jsonData = (JSONArray) jsonObject.get("data");
            JSONObject data = jsonData.getJSONObject(0);
            String location = data.getString("location");
            return location;

        } catch (IOException e) {
            logger.error("ip地址服务接口调用失败", e);
            return null;
        } catch (Exception e) {
            logger.error("ip所属地址获取失败", e);
            return null;
        }
    }

}
