package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加、更新终端请求参数
 *
 * @author 花开
 * @date 2018/2/6
 */
public class AddUpdateEntityParam extends BaseParam {

    private int serviceId;
    private String entityName;
    private String entityDesc;
    private Map<String, Object> columnKeys;

    private AddUpdateEntityParam(Builder builder) {
        this.serviceId = builder.serviceId;
        this.entityName = builder.entityName;
        this.entityDesc = builder.entityDesc;
        this.columnKeys = builder.columnKeys;
    }

    @Override
    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> paramsMap = new HashMap<>(8);
        paramsMap.put(ParamConsts.AK, ak);
        paramsMap.put(ParamConsts.SERVICE_ID, serviceId);
        paramsMap.put(ParamConsts.ENTITY_NAME, entityName);
        paramsMap.put(ParamConsts.ENTITY_DESC, entityDesc);
        if (columnKeys != null) {
            paramsMap.putAll(columnKeys);
        }
        return paramsMap;
    }

    public static final class Builder {
        private int serviceId;
        private String entityName;
        private String entityDesc;
        private Map<String, Object> columnKeys;

        public Builder(int serviceId, String entityName) {
            this.serviceId = serviceId;
            this.entityName = entityName;
        }

        public Builder serviceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        public Builder entityDesc(String entityDesc) {
            this.entityDesc = entityDesc;
            return this;
        }

        public Builder columnKeys(Map<String, Object> columnKeys) {
            this.columnKeys = columnKeys;
            return this;
        }

        public AddUpdateEntityParam build() {
            return new AddUpdateEntityParam(this);
        }
    }
}
