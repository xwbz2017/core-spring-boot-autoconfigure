package com.zskj.core.autoconfigure.wechat.pay.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 关闭订单返回字段
 *
 * @author 花开
 * @date 2018/1/31
 */
@XStreamAlias("xml")
public class CloseOrderResult extends BaseResult {

    /**
     * 业务结果 必填 SUCCESS/FAIL  SUCCESS退款申请接收成功，结果通过退款查询接口查询  FAIL 提交业务失败
     */
    @XStreamAlias("result_code")
    private String resultCode;

    /**
     * 业务结果描述 必填 对于业务执行的详细描述
     */
    @XStreamAlias("result_msg")
    private String resultMsg;

    /**
     * 错误代码 非必填 列表详见错误码列表
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述 非必填 结果信息描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;

    /**
     * 实际会返回该字段，子商户
     */
    @XStreamAlias("sub_mch_id")
    private String subMchId;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }
}
