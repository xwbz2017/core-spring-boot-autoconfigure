package com.zskj.core.autoconfigure.baidu.result;

/**
 * 百度api返回结果
 *
 * @author 花开
 * @date 2018/2/2
 */
public class BaseResult {

    /**
     * 返回状态，0为成功
     */
    private int status;

    /**
     * 对status的中文描述
     */
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return status == 0;
    }
}
