package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据关键字搜索entity，并返回实时位置接口对应的请求参数
 *
 * @author 花开
 * @date 2018/2/6
 */
public class SearchParam {

    private int serviceId;

    private String query;

    private String filter;

    private String sortby;

    private CoordType coordTypeOutput;

    private Integer pageNumber;

    private Integer pageSize;

    private SearchParam(Builder builder) {
        serviceId = builder.serviceId;
        query = builder.query;
        filter = builder.filter;
        sortby = builder.sortby;
        coordTypeOutput = builder.coordTypeOutput;
        pageNumber = builder.pageNumber;
        pageSize = builder.pageSize;
    }

    public Map<String, Object> getParamMap(String ak) {
        Map<String, Object> params = new HashMap<>(8);
        params.put(ParamConsts.AK, ak);
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.QUERY, query);
        params.put(ParamConsts.FILTER, filter);
        params.put(ParamConsts.SORTBY, sortby);
        if (coordTypeOutput != null) {
            params.put(ParamConsts.COORD_TYPE_OUTPUT, coordTypeOutput.name());
        }
        params.put(ParamConsts.PAGE_INDEX, pageNumber);
        params.put(ParamConsts.PAGE_SIZE, pageSize);
        return params;
    }

    public static final class Builder {
        private int serviceId;
        private String query;
        private String filter;
        private String sortby;
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

        public Builder query(String val) {
            query = val;
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

        public SearchParam build() {
            return new SearchParam(this);
        }
    }
}
