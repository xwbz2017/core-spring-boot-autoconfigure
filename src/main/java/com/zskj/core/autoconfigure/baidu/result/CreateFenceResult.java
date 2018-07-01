package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 创建圆形围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class CreateFenceResult extends BaseResult{

    @JSONField(name = "fence_id")
    private int fenceId;

    public int getFenceId() {
        return fenceId;
    }

    public void setFenceId(int fenceId) {
        this.fenceId = fenceId;
    }
}
