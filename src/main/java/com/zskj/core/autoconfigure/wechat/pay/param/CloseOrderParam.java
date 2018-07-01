package com.zskj.core.autoconfigure.wechat.pay.param;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关闭订单请求参数
 *
 * @author 花开
 * @date 2018/1/31
 */
@XStreamAlias("xml")
public class CloseOrderParam extends BaseParam{

    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
