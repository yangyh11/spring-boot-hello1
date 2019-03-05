package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.base.util.HttpClientUtil;
import com.example.base.util.HttpUtil;
import com.example.demo.dao.IpInfoDao;
import com.example.demo.entity.IpInfo;
import com.example.demo.service.IpService;
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

    @Resource
    private IpInfoDao ipInfoDao;

    @Override
    public void updteLocatio() {

        List<IpInfo> ipInfoList = ipInfoDao.getIpInfo();

        String suffix = "http://opendata.baidu.com/api.php";

        for (IpInfo ipInfo : ipInfoList) {

            String param = "query=ip地址&co=&resource_id=6006&t=1412300361645&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery1102026811896078288555_1412299994977&_=1412299994981";

            String ip = ipInfo.getIp();
            param = param.replace("ip地址", ip);

            String url = suffix + "?" + param;

            try {
                byte[] content= HttpClientUtil.httpGet(url, "gbk", null);
//                String contentStr=new String(content, "gbk");
                String contentStr=HttpUtil.httpPost(url, "gbk");
                logger.info("服务响应报文{}", contentStr);
                if (StringUtils.isBlank(contentStr)){
                    Map<String, String> busin_param =new HashMap<>();
                    logger.info("没有返回任何数据");
                }

                String str = contentStr.substring(47, contentStr.length() - 2);
                JSONObject jsonObject = JSON.parseObject(str);
                JSONArray jsonData = (JSONArray) jsonObject.get("data");
                JSONObject data = jsonData.getJSONObject(0);
                String location = data.getString("location");

                ipInfoDao.updateLocation(ipInfo.getId(), location);
                logger.info("更新的id：" + ipInfo.getId() + ",ip:" + ip + ",所属地址是：" + location);

            } catch (IOException e) {
                logger.error("ip地址服务接口调用失败", e);
            } catch (Exception e) {
                logger.error("ip所属地址更新失败", e);
            }
        }
    }
}
