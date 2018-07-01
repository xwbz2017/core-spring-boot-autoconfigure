package com.zskj.core.autoconfigure.baidu.param;

import java.util.Map;

/**
 * description
 *
 * @author 花开
 * @date 2018/2/6
 */
public class UpdateEntityParam {

    private int serviceId;
    private String entityName;
    private String entityDesc;
    private Map<String, Object> columnKeys;

    private UpdateEntityParam(Builder builder) {
        serviceId = builder.serviceId;
        entityName = builder.entityName;
        entityDesc = builder.entityDesc;
        columnKeys = builder.columnKeys;
    }


    public static final class Builder {
        private int serviceId;
        private String entityName;
        private String entityDesc;
        private Map<String, Object> columnKeys;

        public Builder() {
        }

        public Builder serviceId(int val) {
            serviceId = val;
            return this;
        }

        public Builder entityName(String val) {
            entityName = val;
            return this;
        }

        public Builder entityDesc(String val) {
            entityDesc = val;
            return this;
        }

        public Builder columnKeys(Map<String, Object> val) {
            columnKeys = val;
            return this;
        }

        public UpdateEntityParam build() {
            return new UpdateEntityParam(this);
        }


    }
}
