package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.SupplementMode;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询某 entity 一段时间内行驶里程
 *
 * @author 花开
 * @date 2018/2/6
 */
public class GetDistanceParam extends BaseParam{

    private int serviceId;
    private String entityName;
    private long startTime;
    private long endTime;
    private String isProcessed;
    private String processOption;
    private SupplementMode supplementMode;

    private GetDistanceParam(Builder builder) {
        serviceId = builder.serviceId;
        entityName = builder.entityName;
        startTime = builder.startTime;
        endTime = builder.endTime;
        isProcessed = builder.isProcessed;
        processOption = builder.processOption;
        supplementMode = builder.supplementMode;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(8);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.ENTITY_NAME, entityName);
        params.put(ParamConsts.START_TIME, startTime);
        params.put(ParamConsts.END_TIME, endTime);
        params.put(ParamConsts.IS_PROCESSED, isProcessed);
        params.put(ParamConsts.PROCESS_OPTION, processOption);
        if (supplementMode != null) {
            params.put(ParamConsts.SUPPLEMENT_MODE, supplementMode.name());
        }
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String entityName;
        private long startTime;
        private long endTime;
        private String isProcessed;
        private String processOption;
        private SupplementMode supplementMode;

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

        public Builder isProcessed(String val) {
            isProcessed = val;
            return this;
        }

        public Builder processOption(String val) {
            processOption = val;
            return this;
        }

        public Builder supplementMode(SupplementMode val) {
            supplementMode = val;
            return this;
        }

        public GetDistanceParam build() {
            return new GetDistanceParam(this);
        }
    }
}
