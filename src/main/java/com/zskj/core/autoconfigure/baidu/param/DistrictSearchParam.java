package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * DistrictSearch
 *
 * @author 花开
 * @date 2018/2/6
 */
public class DistrictSearchParam extends BaseParam{

    private int serviceId;
    private String keyword;
    private String filter;
    private String sortby;
    private String returnType;
    private CoordType coordTypeOutput;
    private Integer pageNumber;
    private Integer pageSize;

    private DistrictSearchParam(Builder builder) {
        serviceId = builder.serviceId;
        keyword = builder.keyword;
        filter = builder.filter;
        sortby = builder.sortby;
        returnType = builder.returnType;
        coordTypeOutput = builder.coordTypeOutput;
        pageNumber = builder.pageNumber;
        pageSize = builder.pageSize;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(8);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.KEYWORD, keyword);
        params.put(ParamConsts.FILTER, filter);
        params.put(ParamConsts.SORTBY, sortby);
        params.put(ParamConsts.RETURN_TYPE, returnType);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        params.put(ParamConsts.PAGE_INDEX, pageNumber);
        params.put(ParamConsts.PAGE_SIZE, pageSize);
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String keyword;
        private String filter;
        private String sortby;
        private String returnType;
        private CoordType coordTypeOutput;
        private Integer pageNumber;
        private Integer pageSize;

        public Builder(int serviceId, String keyword) {
            this.serviceId = serviceId;
            this.keyword = keyword;
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder keyword(String val) {
            keyword = val;
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

        public Builder returnType(String val) {
            returnType = val;
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

        public DistrictSearchParam build() {
            return new DistrictSearchParam(this);
        }
    }
}
