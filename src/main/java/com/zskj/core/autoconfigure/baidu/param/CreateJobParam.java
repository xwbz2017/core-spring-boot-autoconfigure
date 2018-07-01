package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建一个任务，该任务完成后将返回文件下载地址，供开发者下载
 *
 * @author 花开
 * @date 2018/2/26
 */
public class CreateJobParam extends BaseParam{

    private int serviceId;
    private long startTime;
    private long endTime;
    private CoordType coordTypeOutput;

    private CreateJobParam(Builder builder) {
        serviceId = builder.serviceId;
        startTime = builder.startTime;
        endTime = builder.endTime;
        coordTypeOutput = builder.coordTypeOutput;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(5);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.START_TIME, startTime);
        params.put(ParamConsts.END_TIME, endTime);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        return params;
    }


    public static final class Builder {
        private int serviceId;
        private long startTime;
        private long endTime;
        private CoordType coordTypeOutput;

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

        public CreateJobParam build() {
            return new CreateJobParam(this);
        }
    }
}
