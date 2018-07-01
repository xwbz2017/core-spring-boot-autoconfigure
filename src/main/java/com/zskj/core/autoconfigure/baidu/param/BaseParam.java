package com.zskj.core.autoconfigure.baidu.param;

import java.util.Map;

/**
 * 基本请求参数
 *
 * @author 花开
 * @date 2018/2/6
 */
public abstract class BaseParam {

    public abstract Map<String, Object> getParamsMap(String ak);
}
