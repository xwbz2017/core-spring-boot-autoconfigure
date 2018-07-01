package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class DeleteFenceParam extends BaseParam {

    private int serviceId;
    private String monitoredPerson;
    private String fenceIds;

    private DeleteFenceParam(Builder builder) {
        serviceId = builder.serviceId;
        monitoredPerson = builder.monitoredPerson;
        fenceIds = builder.fenceIds;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(4);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        params.put(ParamConsts.FENCE_IDS, fenceIds);
        return params;
    }


    public static final class Builder {
        private int serviceId;
        private String monitoredPerson;
        private String fenceIds;

        public Builder(int serviceId, String monitoredPerson, String fenceIds) {
            this.serviceId = serviceId;
            this.monitoredPerson = monitoredPerson;
            this.fenceIds = fenceIds;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder monitoredPerson(String val) {
            monitoredPerson = val;
            return this;
        }

        public Builder fenceIds(String val) {
            fenceIds = val;
            return this;
        }

        public DeleteFenceParam build() {
            return new DeleteFenceParam(this);
        }
    }
}
