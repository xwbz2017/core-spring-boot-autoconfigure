package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 批量上传轨迹失败信息
 *
 * @author 花开
 * @date 2018/2/6
 */
public class AddPointsFailInfoResult {

    @JSONField(name = "param_error")
    private JSONArray paramError;

    @JSONField(name = "internal_error")
    private JSONArray internalError;

    public JSONArray getParamError() {
        return paramError;
    }

    public void setParamError(JSONArray paramError) {
        this.paramError = paramError;
    }

    public JSONArray getInternalError() {
        return internalError;
    }

    public void setInternalError(JSONArray internalError) {
        this.internalError = internalError;
    }
}
