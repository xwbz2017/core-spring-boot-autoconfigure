package com.zskj.core.autoconfigure.aliyun.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.zskj.core.autoconfigure.aliyun.AliYunProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * oss工具
 *
 * @author 花开
 * @date 2017/12/12
 */
public class OssCore {

    private Logger logger = LoggerFactory.getLogger(OssCore.class);

    private OSSClient ossClient;
    private AliYunProperties aliYunProperties;
    private String url;

    public OssCore(OSSClient ossClient, AliYunProperties aliYunProperties) {
        this.ossClient = ossClient;
        this.aliYunProperties = aliYunProperties;
        url = "http://" + aliYunProperties.getOssBucketName() + "." + aliYunProperties.getOssEndPoint() + "/%s";
    }

    /**
     * 以流的方式上传资源，返回资源http访问地址
     *
     * @param key         资源名（路径+名）
     * @param inputStream 资源流
     * @return 资源http访问地址
     */
    public String putObject(String key, InputStream inputStream) {
        try {
            ossClient.putObject(aliYunProperties.getOssBucketName(), key, inputStream);
            return String.format(url, key);
        } catch (OSSException oe) {
            logger.error("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
            logger.error("Error Message: {}, Error Code: {}, Request ID: {}, Host ID: {}", oe.getMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            logger.error(oe.getMessage(), oe);
        } catch (ClientException ce) {
            logger.error("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
            logger.error("Error Message: {}", ce.getMessage());
            logger.error(ce.getMessage(), ce);
        }
        return null;
    }
}
