package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建多边形围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class CreatePolygonFenceParam extends BaseParam{

    private int serviceId;
    private String fenceName;
    private String monitoredPerson;
    private String vertexes;
    private CoordType coordType;
    private Integer denoise;

    private CreatePolygonFenceParam(Builder builder) {
        serviceId = builder.serviceId;
        fenceName = builder.fenceName;
        monitoredPerson = builder.monitoredPerson;
        vertexes = builder.vertexes;
        coordType = builder.coordType;
        denoise = builder.denoise;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(7);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.FENCE_NAME, fenceName);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        params.put(ParamConsts.VERTEXES, vertexes);
        if (coordType != null) {
            params.put(ParamConsts.COORD_TYPE, coordType.name());
        }
        params.put(ParamConsts.DENOISE, denoise);
        return params;
    }


    public static final class Builder {
        private int serviceId;
        private String fenceName;
        private String monitoredPerson;
        private String vertexes;
        private CoordType coordType;
        private Integer denoise;

        public Builder(int serviceId, String vertexes, CoordType coordType) {
            this.serviceId = serviceId;
            this.vertexes = vertexes;
            this.coordType = coordType;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder fenceName(String val) {
            fenceName = val;
            return this;
        }

        public Builder monitoredPerson(String val) {
            monitoredPerson = val;
            return this;
        }

        public Builder vertexes(String val) {
            vertexes = val;
            return this;
        }

        public Builder coordType(CoordType val) {
            coordType = val;
            return this;
        }

        public Builder denoise(Integer val) {
            denoise = val;
            return this;
        }

        public CreatePolygonFenceParam build() {
            return new CreatePolygonFenceParam(this);
        }
    }
}
