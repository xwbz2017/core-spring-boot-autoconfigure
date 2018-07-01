package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * bound search
 *
 * @author 花开
 * @date 2018/2/6
 */
public class BoundSearchParam extends BaseParam{

    private int serviceId;
    private String bounds;
    private String filter;
    private String sortby;
    private CoordType coordTypeInput;
    private CoordType coordTypeOutput;
    private Integer pageNumber;
    private Integer pageSize;

    private BoundSearchParam(Builder builder) {
        serviceId = builder.serviceId;
        bounds = builder.bounds;
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
        params.put(ParamConsts.BOUNDS, bounds);
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
        private String bounds;
        private String filter;
        private String sortby;
        private CoordType coordTypeInput;
        private CoordType coordTypeOutput;
        private Integer pageNumber;
        private Integer pageSize;

        public Builder(int serviceId) {
            this.serviceId = serviceId;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder bounds(String val) {
            bounds = val;
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

        public BoundSearchParam build() {
            return new BoundSearchParam(this);
        }
    }
}
