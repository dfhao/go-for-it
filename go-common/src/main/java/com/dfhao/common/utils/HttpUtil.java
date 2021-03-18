package com.dfhao.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * http工具类
 *
 * @author :  dfhao
 * @date :  2021/3/18 10:46
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final int connectTimeout = 120000;
    private static final int socketTimeout = 120000;

    /**
     * json 请求
     *
     * @param url     请求地址
     * @param header  请求头
     * @param jsonStr 请求参数
     * @return 响应
     * @throws IOException 异常
     */
    public static HttpResultModel jsonPost(String url, Map<String, String> header, String jsonStr) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
            httpPost.setConfig(config);
            if (header != null) {
                for (String key : header.keySet()) {
                    httpPost.addHeader(key, header.get(key));
                }
            }
            StringEntity entity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine() != null) {
                if (response.getEntity() != null) {
                    resultString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                }
                return new HttpResultModel(response.getStatusLine().getStatusCode(), resultString);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return new HttpResultModel(520, "不能识别响应结果！！！");
    }

    /**
     * xml 请求
     *
     * @param url    请求地址
     * @param header 请求头
     * @param xmlStr 请求参数
     * @return 返回参数
     * @throws IOException 异常
     */
    public static HttpResultModel xmlPost(String url, Map<String, String> header, String xmlStr) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).setConnectionRequestTimeout(connectTimeout).build();
            httpPost.setConfig(config);
            if (header != null) {
                for (String key : header.keySet()) {
                    httpPost.addHeader(key, header.get(key));
                }
            }
            HttpEntity httpEntity = new StringEntity(xmlStr, StandardCharsets.UTF_8);
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            if (response != null && response.getStatusLine() != null) {
                if (response.getEntity() != null) {
                    resultString = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                }
                return new HttpResultModel(response.getStatusLine().getStatusCode(), resultString);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return new HttpResultModel(520, "不能识别响应结果！！！");
    }
}
