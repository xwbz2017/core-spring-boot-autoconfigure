package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 终端列表请求参数
 *
 * @author 花开
 * @date 2018/2/6
 */
public class ListEntityParam {

    private int serviceId;
    private String filter;
    private CoordType coordTypeOutput;
    private Integer pageNumber;
    private Integer pageSize;

    private ListEntityParam(Builder builder) {
        this.serviceId = builder.serviceId;
        this.filter = builder.filter;
        this.coordTypeOutput = builder.coordTypeOutput;
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
    }


    public static final class Builder {
        private int serviceId;
        private String filter;
        private CoordType coordTypeOutput;
        private Integer pageNumber;
        private Integer pageSize;

        public Builder(int serviceId) {
            this.serviceId = serviceId;
        }

        public Builder serviceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder filter(String filter) {
            this.filter = filter;
            return this;
        }

        public Builder coordTypeOutput(CoordType coordTypeOutput) {
            this.coordTypeOutput = coordTypeOutput;
            return this;
        }

        public Builder pageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ListEntityParam build() {
            return new ListEntityParam(this);
        }
    }

    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> params = new HashMap<>(6);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.FILTER, filter);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        params.put(ParamConsts.PAGE_INDEX, pageNumber);
        params.put(ParamConsts.PAGE_SIZE, pageSize);
        return params;
    }
}
