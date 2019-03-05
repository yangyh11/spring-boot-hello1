package com.example.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-05 10:39
 **/
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String httpPost(String url, String charsetName){

        HttpURLConnection connection = null;
        String line = "";
        String httpResults = "";

        // 打开和URL之间的连接
        URL postUrl = null;
        try {
            postUrl = new URL(url.toString());
            connection = (HttpURLConnection) postUrl.openConnection();
            // 设置通用的请求属性
            connection.setDoOutput(false);//posp请求改为TRUE
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", charsetName);
            connection.setRequestProperty("Accept-Charset", charsetName);

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
            //line=bf.readLine();
            while ((line = reader.readLine()) != null) {
                httpResults = httpResults + line.toString();
            }
            return httpResults;
        } catch (IOException e) {
            logger.error("网络请求异常｛｝", url);
            return null;
        }

    }
}
