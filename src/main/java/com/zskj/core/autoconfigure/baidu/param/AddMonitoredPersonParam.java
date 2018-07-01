package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;

import java.util.HashMap;
import java.util.Map;

/**
 * 增加围栏需监控的entity
 *
 * @author 花开
 * @date 2018/2/26
 */
public class AddMonitoredPersonParam extends BaseParam {

    private int serviceId;
    private int fenceId;
    private String monitoredPerson;

    private AddMonitoredPersonParam(Builder builder) {
        serviceId = builder.serviceId;
        fenceId = builder.fenceId;
        monitoredPerson = builder.monitoredPerson;
    }


    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(4);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.FENCE_ID, fenceId);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        return params;
    }


    public static final class Builder {
        private int serviceId;
        private int fenceId;
        private String monitoredPerson;

        public Builder(int serviceId, int fenceId, String monitoredPerson) {
            this.serviceId = serviceId;
            this.fenceId = fenceId;
            this.monitoredPerson = monitoredPerson;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder fenceId(int val) {
            fenceId = val;
            return this;
        }

        public Builder monitoredPerson(String val) {
            monitoredPerson = val;
            return this;
        }

        public AddMonitoredPersonParam build() {
            return new AddMonitoredPersonParam(this);
        }
    }
}
