package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * around search
 *
 * @author 花开
 * @date 2018/2/6
 */
public class AroundSearchParam extends BaseParam{

    private int serviceId;
    private String center;
    private int radius;
    private String filter;
    private String sortby;
    private CoordType coordTypeInput;
    private CoordType coordTypeOutput;
    private Integer pageNumber;
    private Integer pageSize;

    private AroundSearchParam(Builder builder) {
        serviceId = builder.serviceId;
        center = builder.center;
        radius = builder.radius;
        filter = builder.filter;
        sortby = builder.sortby;
        coordTypeInput = builder.coordTypeInput;
        coordTypeOutput = builder.coordTypeOutput;
        pageNumber = builder.pageNumber;
        pageSize = builder.pageSize;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(8);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.CENTER, center);
        params.put(ParamConsts.RADIUS, radius);
        params.put(ParamConsts.FILTER, filter);
        params.put(ParamConsts.SORTBY, sortby);
        if (coordTypeInput != null) {
            params.put(ParamConsts.COORD_TYPE_INPUT, coordTypeInput.name());
        }
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        params.put(ParamConsts.PAGE_INDEX, pageNumber);
        params.put(ParamConsts.PAGE_SIZE, pageSize);
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String center;
        private int radius;
        private String filter;
        private String sortby;
        private CoordType coordTypeInput;
        private CoordType coordTypeOutput;
        private Integer pageNumber;
        private Integer pageSize;

        public Builder(int serviceId, String center, int radius) {
            this.serviceId = serviceId;
            this.center = center;
            this.radius = radius;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder center(String val) {
            center = val;
            return this;
        }

        public Builder radius(int val) {
            radius = val;
            return this;
        }

        public Builder filter(String val) {
            filter = val;
            return this;
        }

        public Builder sortby(String val) {
            sortby = val;
            return this;
        }

        public Builder coordTypeInput(CoordType val) {
            coordTypeInput = val;
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

        public AroundSearchParam build() {
            return new AroundSearchParam(this);
        }
    }
}
