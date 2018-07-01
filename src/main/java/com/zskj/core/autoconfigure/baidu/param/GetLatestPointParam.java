package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 查找某 entity纠偏后的实时位置，以及所在道路的限速信息。适用于持续追踪某一终端，展示纠偏后的实时位置，并实时判断车辆是否超速。
 *
 * @author 花开
 * @date 2018/2/6
 */
public class GetLatestPointParam extends BaseParam{

    private int serviceId;
    private String entityName;
    private String processOption;
    private CoordType coordTypeOutput;

    private GetLatestPointParam(Builder builder) {
        serviceId = builder.serviceId;
        entityName = builder.entityName;
        processOption = builder.processOption;
        coordTypeOutput = builder.coordTypeOutput;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(4);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.ENTITY_NAME, entityName);
        params.put(ParamConsts.PROCESS_OPTION, processOption);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String entityName;
        private String processOption;
        private CoordType coordTypeOutput;

        public Builder(int serviceId, String entityName) {
            this.serviceId = serviceId;
            this.entityName = entityName;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder entityName(String val) {
            entityName = val;
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

        public GetLatestPointParam build() {
            return new GetLatestPointParam(this);
        }
    }
}
