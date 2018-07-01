package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;

import java.util.HashMap;
import java.util.Map;

/**
 * 更新行政区划围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class UpdateDistrictFenceParam extends BaseParam{

    private int serviceId;
    private int fenceId;
    private String fenceName;
    private String monitoredPerson;
    private String keyword;
    private Integer denoise;

    private UpdateDistrictFenceParam(Builder builder) {
        serviceId = builder.serviceId;
        fenceId = builder.fenceId;
        fenceName = builder.fenceName;
        monitoredPerson = builder.monitoredPerson;
        keyword = builder.keyword;
        denoise = builder.denoise;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(7);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.FENCE_ID, fenceId);
        params.put(ParamConsts.FENCE_NAME, fenceName);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        params.put(ParamConsts.KEYWORD, keyword);
        params.put(ParamConsts.DENOISE, denoise);
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private int fenceId;
        private String fenceName;
        private String monitoredPerson;
        private String keyword;
        private Integer denoise;

        public Builder(int serviceId, int fenceId, String keyword) {
            this.serviceId = serviceId;
            this.fenceId = fenceId;
            this.keyword = keyword;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder fenceId(int val) {
            fenceId = val;
            return this;
        }

        public Builder fenceName(String val) {
            fenceName = val;
            return this;
        }

        public Builder monitoredPerson(String val) {
            monitoredPerson = val;
            return this;
        }

        public Builder keyword(String val) {
            keyword = val;
            return this;
        }

        public Builder denoise(Integer val) {
            denoise = val;
            return this;
        }

        public UpdateDistrictFenceParam build() {
            return new UpdateDistrictFenceParam(this);
        }
    }
}
