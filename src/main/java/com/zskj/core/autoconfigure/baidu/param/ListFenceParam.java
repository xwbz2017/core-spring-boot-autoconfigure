package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class ListFenceParam extends BaseParam{

    private int serviceId;
    private String monitoredPerson;
    private String fenceIds;
    private CoordType coordTypeOutput;

    private ListFenceParam(Builder builder) {
        serviceId = builder.serviceId;
        monitoredPerson = builder.monitoredPerson;
        fenceIds = builder.fenceIds;
        coordTypeOutput = builder.coordTypeOutput;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(5);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        params.put(ParamConsts.FENCE_IDS, fenceIds);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        return params;
    }


    public static final class Builder {
        private int serviceId;
        private String monitoredPerson;
        private String fenceIds;
        private CoordType coordTypeOutput;

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

        public Builder coordTypeOutput(CoordType val) {
            coordTypeOutput = val;
            return this;
        }

        public ListFenceParam build() {
            return new ListFenceParam(this);
        }
    }
}
