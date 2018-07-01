package com.zskj.core.autoconfigure.aliyun.mns;

import com.alibaba.fastjson.JSON;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息队列测试
 *
 * @author 花开
 * @date 2018/2/2
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MnsQueueCoreTest {

    private Logger logger = LoggerFactory.getLogger(MnsQueueCoreTest.class);

    @Autowired
    private MnsQueueCore queueCore;

    private String queueName = "testmq";

    @Test
    public void sendMessage() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("type", 0);
        map.put("id", 200);
        Message message = queueCore.sendMessage(queueName, JSON.toJSONString(map));

        // {"messageBodyMD5":"6294CA12708415BE02D4636F9377D5FA","messageId":"D43715902B261DF7-1-16155C31EDC-300000012","requestId":"5A7429C22372C2C951E24666"}
        logger.info(JSON.toJSONString(message));
    }

    @Test
    public void createQueue() throws Exception{
        QueueMeta meta = new QueueMeta();
        meta.setQueueName("test-core");
        String url = queueCore.createQueue(meta);

        // http://1549135646902295.mns.cn-beijing.aliyuncs.com/queues/test-core
        logger.info(url);
    }
}
