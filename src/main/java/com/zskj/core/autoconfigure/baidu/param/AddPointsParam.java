package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;

import java.util.HashMap;
import java.util.Map;

/**
 * 一次上传多个轨迹点.可上传一个 entity 的多个轨迹点，或多个entity的多个轨迹点，并且可以携带自定义字段的信息。
 *
 * @author 花开
 * @date 2018/2/6
 */
public class AddPointsParam extends BaseParam {

    private int serviceId;
    private String pointList;

    private AddPointsParam(Builder builder) {
        serviceId = builder.serviceId;
        pointList = builder.pointList;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(3);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.POINT_LIST, pointList);
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String pointList;

        public Builder(int serviceId, String pointList) {
            this.serviceId = serviceId;
            this.pointList = pointList;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder pointList(String val) {
            pointList = val;
            return this;
        }

        public AddPointsParam build() {
            return new AddPointsParam(this);
        }
    }
}
