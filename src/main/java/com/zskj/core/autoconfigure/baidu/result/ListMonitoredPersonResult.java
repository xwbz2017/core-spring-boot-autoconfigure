package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 查询围栏监控的所有entity
 *
 * @author 花开
 * @date 2018/2/26
 */
public class ListMonitoredPersonResult extends BaseResult{

    private int total;

    private int size;

    @JSONField(name = "monitored_person")
    private JSONArray monitoredPersons;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public JSONArray getMonitoredPersons() {
        return monitoredPersons;
    }

    public void setMonitoredPersons(JSONArray monitoredPersons) {
        this.monitoredPersons = monitoredPersons;
    }
}
