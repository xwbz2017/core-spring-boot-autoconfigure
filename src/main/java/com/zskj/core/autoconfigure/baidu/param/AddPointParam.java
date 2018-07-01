package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 为一个entity上传一个轨迹点
 *
 * @author 花开
 * @date 2018/2/6
 */
public class AddPointParam extends BaseParam{

    private int serviceId;
    private String entityName;
    private double latitude;
    private double longitude;
    private long locTime;
    private CoordType coordTypeInput;
    private Double speed;
    private Integer direction;
    private Double height;
    private Double radius;
    private String objectName;
    private Map<String, Object> columnKeys;

    private AddPointParam(Builder builder) {
        serviceId = builder.serviceId;
        entityName = builder.entityName;
        latitude = builder.latitude;
        longitude = builder.longitude;
        locTime = builder.locTime;
        coordTypeInput = builder.coordTypeInput;
        speed = builder.speed;
        direction = builder.direction;
        height = builder.height;
        radius = builder.radius;
        objectName = builder.objectName;
        columnKeys = builder.columnKeys;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(14);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.ENTITY_NAME, entityName);
        params.put(ParamConsts.LATITUDE, latitude);
        params.put(ParamConsts.LONGITUDE, longitude);
        params.put(ParamConsts.LOC_TIME, locTime);
        if (coordTypeInput != null) {
            params.put(ParamConsts.COORD_TYPE_INPUT, coordTypeInput.name());
        }
        params.put(ParamConsts.SPEED, speed);
        params.put(ParamConsts.DIRECTION, direction);
        params.put(ParamConsts.HEIGHT, height);
        params.put(ParamConsts.RADIUS, radius);
        params.put(ParamConsts.OBJECT_NAME, objectName);
        if (columnKeys != null) {
            params.putAll(columnKeys);
        }
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String entityName;
        private double latitude;
        private double longitude;
        private long locTime;
        private CoordType coordTypeInput;
        private Double speed;
        private Integer direction;
        private Double height;
        private Double radius;
        private String objectName;
        private Map<String, Object> columnKeys;

        public Builder(int serviceId, String entityName, double latitude, double longitude, long locTime, CoordType coordTypeInput) {
            this.serviceId = serviceId;
            this.entityName = entityName;
            this.latitude = latitude;
            this.longitude = longitude;
            this.locTime = locTime;
            this.coordTypeInput = coordTypeInput;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder entityName(String val) {
            entityName = val;
            return this;
        }

        public Builder latitude(double val) {
            latitude = val;
            return this;
        }

        public Builder longitude(double val) {
            longitude = val;
            return this;
        }

        public Builder locTime(long val) {
            locTime = val;
            return this;
        }

        public Builder coordTypeInput(CoordType val) {
            coordTypeInput = val;
            return this;
        }

        public Builder speed(Double val) {
            speed = val;
            return this;
        }

        public Builder direction(Integer val) {
            direction = val;
            return this;
        }

        public Builder height(Double val) {
            height = val;
            return this;
        }

        public Builder radius(Double val) {
            radius = val;
            return this;
        }

        public Builder objectName(String val) {
            objectName = val;
            return this;
        }

        public Builder columnKeys(Map<String, Object> val) {
            columnKeys = val;
            return this;
        }

        public AddPointParam build() {
            return new AddPointParam(this);
        }
    }
}
