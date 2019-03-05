package com.example.base.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: HttpClient工具类；支持http/https、单向/双向认证；默认执行后自动关闭
 * @author: yangyh
 * @create: 2019-03-04 10:34
 **/
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static Charset charset = StandardCharsets.UTF_8;
    private static ContentType content_type = ContentType.APPLICATION_FORM_URLENCODED.withCharset(charset);
    private static PoolingHttpClientConnectionManager connManager;
    /**
     * 代理地址
     **/
    private static HttpHost proxyHost;
    /**
     * 60秒
     **/
    private static int timeout = 60 * 1000;

    /**
     * POST方式请求，支持http/https
     **/
    public static byte[] httpPost(String url, Map<String, Object> params, String charsetName, List<Header> headers) throws ClientProtocolException, IOException {
        HttpPost httppost = new HttpPost(url);
        HttpEntity reqEntity = buildHttpEntry(params);
        httppost.setEntity(reqEntity);
        return execute(httppost, charsetName, headers);
    }

    /**
     * Post方式请求，支持http/https
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static byte[] httpPost(String url, String charsetName, List<Header> headers) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        return execute(httpPost, charsetName, headers);
    }

    /**
     * GET方式请求，支持http/https
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static byte[] httpGet(String url, String charsetName, List<Header> headers) throws ClientProtocolException, IOException {
        HttpGet httpget = new HttpGet(url);
        return execute(httpget, charsetName, headers);
    }


    /**
     * 构造普通http请求实体
     *
     * @param obj
     * @return
     */
    private static HttpEntity buildHttpEntry(Object obj) {
        EntityBuilder builder = EntityBuilder.create();
        builder.setContentEncoding(HttpClientUtil.charset.name());
        builder.setContentType(HttpClientUtil.content_type);
        if (obj instanceof Map) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) obj).entrySet()) {
                if (entry.getValue() == null) {
                    continue;
                }
                if (entry.getValue() instanceof List) {
                    for (Object val : (List) entry.getValue()) {
                        list.add(new BasicNameValuePair(entry.getKey(), (String) val));
                    }
                } else if (entry.getValue().getClass().isArray()) {
                    for (Object val : (Object[]) entry.getValue()) {
                        list.add(new BasicNameValuePair(entry.getKey(), (String) val));
                    }
                } else {
                    list.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                }
            }
            builder.setParameters(list);
        } else {
            builder.setText((String) obj);
        }
        return builder.build();
    }

    /**
     * 执行http请求，解析输出流为字节
     *
     * @param request
     * @param headers 不能为null，否则不能把执行post请求得到的header放进去
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    private static byte[] execute(HttpRequestBase request, String charsetName, List<Header> headers) throws ClientProtocolException, IOException {
        if (headers != null && headers.size() > 0) {
            // 添加请求头
            for (Header header : headers) {
                request.addHeader(header);
            }
        } else if (headers == null) {
            // 如果入参的headers为null,后面headers.add(header)是返回不出去的。这一句只是为了避免空指针异常
            headers = new ArrayList<Header>();
        }

        HttpClientBuilder httpBuilder = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy());
        httpBuilder.setConnectionManager(connManager);
        // 设置重试
        httpBuilder.setRetryHandler(new HttpRequestRetryHandler() {

            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount > 3) {
                    logger.warn("Maximum tries reached for client http pool ");
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    // NoHttpResponseException 重试
                    logger.warn("NoHttpResponseException on " + executionCount + " call");
                    return true;
                }
                return false;
            }
        });

        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            if (HttpClientUtil.getProxyHost() != null) {
                httpclient = httpBuilder.setProxy(getProxyHost()).build();
            } else {
                httpclient = httpBuilder.build();
            }
            request.getParams().setParameter("Connection", "close");
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
            request.setConfig(requestConfig);
            response = httpclient.execute(request);
            for (Header header : response.getAllHeaders()) {
                if ("Set-Cookie".equalsIgnoreCase(header.getName())) {
                    // 回写返回的cookie
                    headers.add(header);
                }
//                if(Constants.LOGGER_ERROR_PATH.equalsIgnoreCase(header.getName())){
//                    headers.add(header);
//                }
            }
            if (response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
                HttpEntity entity = response.getEntity();
                byte[] data = EntityUtils.toByteArray(entity);
                return data;
            } else {
                HttpEntity entity = response.getEntity();
                byte[] data = null;
                try {
                    data = EntityUtils.toByteArray(entity);
                } catch (Exception ignore) {
                }
                data = data == null ? ("网络请求发生异常:" + response.getStatusLine() + " URI=" + request.getURI()).getBytes() : data;
                logger.warn("网络请求发生异常:" + new String(data, charsetName));
                return null;
            }
        } finally {
            if (request != null) {
                try {
                    request.completed();
                    request.abort();
                } catch (Exception e) {
                    logger.warn("关闭request发生错误", e);
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    logger.warn("关闭response发生错误", e);
                }
            }
        }
    }

    public static HttpHost getProxyHost() {
        return proxyHost;
    }

}
