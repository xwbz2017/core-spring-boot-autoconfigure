package com.zskj.core.autoconfigure.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具库
 * 基于httpclient4.5+
 *
 * @author 花开
 * @date 2018/1/29
 */
public class HttpClientCore {

    private Logger logger = LoggerFactory.getLogger(HttpClientCore.class);

    private RequestConfig requestConfig;
    private CloseableHttpClient httpClient;

    public HttpClientCore(HttpClientProperties httpClientProperties, PoolingHttpClientConnectionManager clientConnectionManager) {
        this.requestConfig = RequestConfig.custom()
                .setSocketTimeout(httpClientProperties.getSocketTimeout())
                .setConnectTimeout(httpClientProperties.getConnectTimeout())
                .setConnectionRequestTimeout(httpClientProperties.getConnectRequestTimeout())
                .build();
        this.httpClient = HttpClients.custom()
                .setConnectionManager(clientConnectionManager).setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * get请求
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param params  请求参数
     * @return 返回200时的响应文本
     */
    public @CheckForNull
    String doGet(@Nonnull String url, Header[] headers, Map<String, Object> params) {
        HttpGet httpGet = new HttpGet(buildUrlParams(url, params));
        httpGet.setConfig(requestConfig);
        httpGet.setHeaders(headers);
        return execute(httpGet);
    }

    /**
     * post请求，form提交方式
     *
     * @param url     请求地址
     * @param headers 请求头
     * @param params  请求参数
     * @return 返回200时的响应文本
     */
    public @CheckForNull
    String doPostForm(@Nonnull String url, Header[] headers, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeaders(headers);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                nameValuePairs.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            return execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * post请求，json文本提交
     *
     * @param url        请求地址
     * @param headers    请求头
     * @param jsonParams json参数
     * @return 返回200时的响应文本
     */
    public @CheckForNull
    String doPostJson(@Nonnull String url, Header[] headers, String jsonParams) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeaders(headers);
        httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        if (jsonParams != null) {
            httpPost.setEntity(new StringEntity(jsonParams, ContentType.APPLICATION_JSON));
        }
        return execute(httpPost);
    }

    /**
     * 执行http请求
     *
     * @param httpRequest http请求
     * @return 返回200时的响应文本
     */
    private @CheckForNull
    String execute(@Nonnull HttpUriRequest httpRequest) {
        try (CloseableHttpResponse response = httpClient.execute(httpRequest)) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
                return result;
            } else {
                logger.warn("响应状态码为：{}", response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 在url后拼接查询参数
     *
     * @param url    请求地址
     * @param params 参数
     * @return url?params
     */
    private @Nonnull
    String buildUrlParams(@Nonnull String url, Map<String, Object> params) {
        try {
            if (params == null || params.isEmpty()) {
                return url;
            }
            StringBuilder sbParams = new StringBuilder();
            for (String paramName : params.keySet()) {
                if (StringUtils.isBlank(paramName)) {
                    continue;
                }
                if (sbParams.length() > 0) {
                    sbParams.append("&");
                } else {
                    sbParams.append("?");
                }
                sbParams.append(paramName);
                sbParams.append("=");
                Object value = params.get(paramName);
                if (value != null) {
                    sbParams.append(URLEncoder.encode(value.toString(), "UTF-8"));
                }
            }
            return url + sbParams.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return url;
    }
}
