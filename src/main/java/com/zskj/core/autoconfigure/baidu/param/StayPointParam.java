package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询entity在指定时间段内的停留点。停留点判断规则为：在stay_radius半径范围内，滞留start_time以上，被认为是一次停留，将取一个代表性坐标作为停留点，其中 stay_radius 默认为20米，start_time 默认为 600秒。
 *
 * @author 花开
 * @date 2018/2/26
 */
public class StayPointParam extends BaseParam{

    private int serviceId;
    private String entityName;
    private long startTime;
    private long endTime;
    private Integer stayTime;
    private Integer stayRadius;
    private String processOption;
    private CoordType coordTypeOutput;

    private StayPointParam(Builder builder) {
        serviceId = builder.serviceId;
        entityName = builder.entityName;
        startTime = builder.startTime;
        endTime = builder.endTime;
        stayTime = builder.stayTime;
        stayRadius = builder.stayRadius;
        processOption = builder.processOption;
        coordTypeOutput = builder.coordTypeOutput;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(9);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.ENTITY_NAME, entityName);
        params.put(ParamConsts.START_TIME, startTime);
        params.put(ParamConsts.END_TIME, endTime);
        params.put(ParamConsts.STAY_TIME, stayTime);
        params.put(ParamConsts.STAY_RADIUS, stayRadius);
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
        private Integer stayTime;
        private Integer stayRadius;
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

        public Builder stayTime(Integer val) {
            stayTime = val;
            return this;
        }

        public Builder stayRadius(Integer val) {
            stayRadius = val;
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

        public StayPointParam build() {
            return new StayPointParam(this);
        }
    }
}
