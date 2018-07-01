package com.zskj.core.autoconfigure.baidu.param;

import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除终端请求参数
 *
 * @author 花开
 * @date 2018/2/6
 */
public class DeleteEntityParam {

    private int serviceId;
    private String entityName;

    private DeleteEntityParam(Builder builder) {
        this.serviceId = builder.serviceId;
        this.entityName = builder.entityName;
    }

    public static final class Builder {
        private int serviceId;
        private String entityName;

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

        public DeleteEntityParam build() {
            return new DeleteEntityParam(this);
        }
    }

    public Map<String, Object> getParamsMap(String ak) {
        Map<String, Object> paramsMap = new HashMap<>(3);
        paramsMap.put(ParamConsts.AK, ak);
        paramsMap.put(ParamConsts.SERVICE_ID, serviceId);
        paramsMap.put(ParamConsts.ENTITY_NAME, entityName);
        return paramsMap;
    }
}
