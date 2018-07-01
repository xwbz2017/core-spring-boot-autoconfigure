package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建圆形围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class CreateCircleFenceParam extends BaseParam {

    private int serviceId;
    private String fenceName;
    private String monitoredPerson;
    private double longitude;
    private double latitude;
    private double radius;
    private CoordType coordType;
    private Integer denoise;

    private CreateCircleFenceParam(Builder builder) {
        serviceId = builder.serviceId;
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
        Map<String, Object> params = new HashMap<>(9);
        params.put(ParamConsts.AK, ak);
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
        private String fenceName;
        private String monitoredPerson;
        private double longitude;
        private double latitude;
        private double radius;
        private CoordType coordType;
        private Integer denoise;

        public Builder(int serviceId, double longitude, double latitude, double radius, CoordType coordType) {
            this.serviceId = serviceId;
            this.longitude = longitude;
            this.latitude = latitude;
            this.radius = radius;
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

        public Builder longitude(double val) {
            longitude = val;
            return this;
        }

        public Builder latitude(double val) {
            latitude = val;
            return this;
        }

        public Builder radius(double val) {
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

        public CreateCircleFenceParam build() {
            return new CreateCircleFenceParam(this);
        }
    }
}
