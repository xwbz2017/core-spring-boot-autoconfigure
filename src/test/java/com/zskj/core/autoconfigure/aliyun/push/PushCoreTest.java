package com.zskj.core.autoconfigure.aliyun.push;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.push.model.v20160801.QueryDeviceInfoResponse;
import com.zskj.core.autoconfigure.aliyun.push.enums.ApnsEnv;
import com.zskj.core.autoconfigure.aliyun.push.enums.Target;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 推送测试
 *
 * @author 花开
 * @date 2018/2/2
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PushCoreTest {

    private Logger logger = LoggerFactory.getLogger(PushCoreTest.class);

    @Autowired
    private PushCore pushCore;
    private String deviceId = "6d1dbe5476354477afa62d326cfdf88d";
    private String androidDeviceId = "e45cf31e6fb3419ebb734f6f0744789e";
    private String title = "这是一个标题哦";
    private String body = "这里存放内容哦";

    @Test
    public void pushMessageToIOS() throws Exception{
        String messageId = pushCore.pushMessageToIOS(Target.DEVICE, deviceId, title, body);

        logger.info("messageId:{}", messageId);
    }

    @Test
    public void pushNoticeToIOS() throws Exception{
        String messageId = pushCore.pushNoticeToIOS(Target.DEVICE, deviceId, title, body, ApnsEnv.PRODUCT, null);

        logger.info("messageId:{}", messageId);
    }

    @Test
    public void pushNoticeToAndroid() throws Exception {
        String messageId = pushCore.pushNoticeToAndroid(Target.DEVICE, androidDeviceId, title, body, null);
        logger.info("messageId:{}", messageId);
    }

    @Test
    public void queryDeviceInfo() throws Exception{

        QueryDeviceInfoResponse.DeviceInfo deviceInfo = pushCore.queryDeviceInfo(deviceId);
        logger.info(JSON.toJSONString(deviceInfo));

        QueryDeviceInfoResponse.DeviceInfo androidDeviceInfo = pushCore.queryDeviceInfo(androidDeviceId);
        logger.info(JSON.toJSONString(androidDeviceInfo));
    }
}
