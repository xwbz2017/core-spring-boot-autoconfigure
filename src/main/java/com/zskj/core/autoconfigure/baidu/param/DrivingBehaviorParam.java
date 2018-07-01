package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 驾驶行为分析
 *
 * @author 花开
 * @date 2018/2/26
 */
public class DrivingBehaviorParam extends BaseParam {

    private int serviceId;
    private String entityName;
    private long startTime;
    private long endTime;
    private Double speedingThreshold;
    private Double harshAccelerationThreshold;
    private Double harshBreakingThreshold;
    private Double harshSteeringThreshold;
    private String processOption;
    private CoordType coordTypeOutput;

    private DrivingBehaviorParam(Builder builder) {
        serviceId = builder.serviceId;
        entityName = builder.entityName;
        startTime = builder.startTime;
        endTime = builder.endTime;
        speedingThreshold = builder.speedingThreshold;
        harshAccelerationThreshold = builder.harshAccelerationThreshold;
        harshBreakingThreshold = builder.harshBreakingThreshold;
        harshSteeringThreshold = builder.harshSteeringThreshold;
        processOption = builder.processOption;
        coordTypeOutput = builder.coordTypeOutput;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(11);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.ENTITY_NAME, entityName);
        params.put(ParamConsts.START_TIME, startTime);
        params.put(ParamConsts.END_TIME, endTime);
        params.put(ParamConsts.SPEEDING_THRESHOLD, speedingThreshold);
        params.put(ParamConsts.HARSH_ACCELERATION_THRESHOLD, harshAccelerationThreshold);
        params.put(ParamConsts.HARSH_BREAKING_THRESHOLD, harshBreakingThreshold);
        params.put(ParamConsts.HARSH_STEERING_THRESHOLD, harshSteeringThreshold);
        params.put(ParamConsts.PROCESS_OPTION, processOption);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String entityName;
        private long startTime;
        private long endTime;
        private Double speedingThreshold;
        private Double harshAccelerationThreshold;
        private Double harshBreakingThreshold;
        private Double harshSteeringThreshold;
        private String processOption;
        private CoordType coordTypeOutput;

        public Builder(int serviceId, String entityName, long startTime, long endTime) {
            this.serviceId = serviceId;
            this.entityName = entityName;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder entityName(String val) {
            entityName = val;
            return this;
        }

        public Builder startTime(long val) {
            startTime = val;
            return this;
        }

        public Builder endTime(long val) {
            endTime = val;
            return this;
        }

        public Builder speedingThreshold(Double val) {
            speedingThreshold = val;
            return this;
        }

        public Builder harshAccelerationThreshold(Double val) {
            harshAccelerationThreshold = val;
            return this;
        }

        public Builder harshBreakingThreshold(Double val) {
            harshBreakingThreshold = val;
            return this;
        }

        public Builder harshSteeringThreshold(Double val) {
            harshSteeringThreshold = val;
            return this;
        }

        public Builder processOption(String val) {
            processOption = val;
            return this;
        }

        public Builder coordTypeOutput(CoordType val) {
            coordTypeOutput = val;
            return this;
        }

        public DrivingBehaviorParam build() {
            return new DrivingBehaviorParam(this);
        }
    }
}
