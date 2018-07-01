package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author 花开
 * @date 2018/2/26
 */
public class UpdateCircleFenceParam extends BaseParam {

    private int serviceId;
    private int fenceId;
    private String fenceName;
    private String monitoredPerson;
    private Double longitude;
    private Double latitude;
    private Double radius;
    private CoordType coordType;
    private Integer denoise;

    private UpdateCircleFenceParam(Builder builder) {
        serviceId = builder.serviceId;
        fenceId = builder.fenceId;
        fenceName = builder.fenceName;
        monitoredPerson = builder.monitoredPerson;
        longitude = builder.longitude;
        latitude = builder.latitude;
        radius = builder.radius;
        coordType = builder.coordType;
        denoise = builder.denoise;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(10);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.FENCE_ID, fenceId);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.FENCE_NAME, fenceName);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        params.put(ParamConsts.LONGITUDE, longitude);
        params.put(ParamConsts.LATITUDE, latitude);
        params.put(ParamConsts.RADIUS, radius);
        if (coordType != null) {
            params.put(ParamConsts.COORD_TYPE, coordType.name());
        }
        params.put(ParamConsts.DENOISE, denoise);
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private int fenceId;
        private String fenceName;
        private String monitoredPerson;
        private Double longitude;
        private Double latitude;
        private Double radius;
        private CoordType coordType;
        private Integer denoise;

        public Builder(int serviceId, int fenceId, Double longitude, Double latitude, Double radius, CoordType coordType) {
            this.serviceId = serviceId;
            this.fenceId = fenceId;
            this.longitude = longitude;
            this.latitude = latitude;
            this.radius = radius;
            this.coordType = coordType;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder fenceId(int val) {
            fenceId = val;
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

        public Builder longitude(Double val) {
            longitude = val;
            return this;
        }

        public Builder latitude(Double val) {
            latitude = val;
            return this;
        }

        public Builder radius(Double val) {
            radius = val;
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

        public UpdateCircleFenceParam build() {
            return new UpdateCircleFenceParam(this);
        }
    }
}
