package com.zskj.core.autoconfigure.wechat.pay.test;

import com.alibaba.fastjson.JSON;
import com.zskj.core.autoconfigure.wechat.pay.WechatPayCore;
import com.zskj.core.autoconfigure.wechat.pay.result.CloseOrderResult;
import com.zskj.core.autoconfigure.wechat.pay.result.OrderQueryResult;
import com.zskj.core.autoconfigure.wechat.pay.result.RefundQueryResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * description
 *
 * @author 花开
 * @date 2018/1/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WechatPayCoreTest {

    @Autowired
    private WechatPayCore wechatPayCore;

    @Test
    public void refundQuery() throws Exception{

        Assert.assertNotNull(wechatPayCore);

        RefundQueryResult refundQueryResult = wechatPayCore.refundQuery("4200000081201801292960003380", null, null, null, null);

        System.out.println(JSON.toJSONString(refundQueryResult));
    }

    @Test
    public void orderQuery() throws Exception {
        OrderQueryResult orderQueryResult = wechatPayCore.orderQuery("4200000081201801292960003380", null);

        System.out.println(JSON.toJSONString(orderQueryResult));
    }

    @Test
    public void closeOrder() throws Exception {
        CloseOrderResult closeOrderResult = wechatPayCore.closeOrder("1801300036");

        System.out.println(JSON.toJSONString(closeOrderResult));
    }
}
