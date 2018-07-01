package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 批量查询所有围栏报警信息
 *
 * @author 花开
 * @date 2018/2/26
 */
public class BatchHistoryAlarmParam extends BaseParam{

    private int serviceId;
    private long startTime;
    private long endTime;
    private CoordType coordTypeOutput;
    private Integer pageNumber;
    private Integer pageSize;

    private BatchHistoryAlarmParam(Builder builder) {
        serviceId = builder.serviceId;
        startTime = builder.startTime;
        endTime = builder.endTime;
        coordTypeOutput = builder.coordTypeOutput;
        pageNumber = builder.pageNumber;
        pageSize = builder.pageSize;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(7);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.START_TIME, startTime);
        params.put(ParamConsts.END_TIME, endTime);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput);
        }
        params.put(ParamConsts.PAGE_INDEX, pageNumber);
        params.put(ParamConsts.PAGE_SIZE, pageSize);
        return params;
    }


    public static final class Builder {
        private int serviceId;
        private long startTime;
        private long endTime;
        private CoordType coordTypeOutput;
        private Integer pageNumber;
        private Integer pageSize;

        public Builder(int serviceId, long startTime, long endTime) {
            this.serviceId = serviceId;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public Builder serviceId(int val) {
            serviceId = val;
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

        public Builder coordTypeOutput(CoordType val) {
            coordTypeOutput = val;
            return this;
        }

        public Builder pageNumber(Integer val) {
            pageNumber = val;
            return this;
        }

        public Builder pageSize(Integer val) {
            pageSize = val;
            return this;
        }

        public BatchHistoryAlarmParam build() {
            return new BatchHistoryAlarmParam(this);
        }
    }
}
