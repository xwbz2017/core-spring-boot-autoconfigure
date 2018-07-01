package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询某监控对象的历史报警信息
 *
 * @author 花开
 * @date 2018/2/26
 */
public class HistoryAlarmParam extends BaseParam{

    private int serviceId;
    private String monitoredPerson;
    private String fenceIds;
    private Long startTime;
    private Long endTime;
    private CoordType coordTypeOutput;

    private HistoryAlarmParam(Builder builder) {
        serviceId = builder.serviceId;
        monitoredPerson = builder.monitoredPerson;
        fenceIds = builder.fenceIds;
        startTime = builder.startTime;
        endTime = builder.endTime;
        coordTypeOutput = builder.coordTypeOutput;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(7);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        params.put(ParamConsts.FENCE_IDS, fenceIds);
        params.put(ParamConsts.START_TIME, startTime);
        params.put(ParamConsts.END_TIME, endTime);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput);
        }
        return params;
    }


    public static final class Builder {
        private int serviceId;
        private String monitoredPerson;
        private String fenceIds;
        private Long startTime;
        private Long endTime;
        private CoordType coordTypeOutput;

        public Builder(int serviceId, String monitoredPerson) {
            this.serviceId = serviceId;
            this.monitoredPerson = monitoredPerson;
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

        public Builder startTime(Long val) {
            startTime = val;
            return this;
        }

        public Builder endTime(Long val) {
            endTime = val;
            return this;
        }

        public Builder coordTypeOutput(CoordType val) {
            coordTypeOutput = val;
            return this;
        }

        public HistoryAlarmParam build() {
            return new HistoryAlarmParam(this);
        }
    }
}
