package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 批量上传轨迹的响应结果
 *
 * @author 花开
 * @date 2018/2/6
 */
public class AddPointsResult extends BaseResult{

    @JSONField(name = "success_num")
    private int successNum;

    @JSONField(name = "fail_info")
    private AddPointsFailInfoResult addPointsFailInfoResult;

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public AddPointsFailInfoResult getAddPointsFailInfoResult() {
        return addPointsFailInfoResult;
    }

    public void setAddPointsFailInfoResult(AddPointsFailInfoResult addPointsFailInfoResult) {
        this.addPointsFailInfoResult = addPointsFailInfoResult;
    }
}
