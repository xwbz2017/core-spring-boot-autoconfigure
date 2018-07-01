package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 搜索返回结果
 *
 * @author 花开
 * @date 2018/2/5
 */
public class SearchResult extends BaseResult {

    private int total;

    private int size;

    private JSONArray entities;

    @JSONField(name = "district_list")
    private List<String> districtList;

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

    public JSONArray getEntities() {
        return entities;
    }

    public void setEntities(JSONArray entities) {
        this.entities = entities;
    }

    public List<String> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<String> districtList) {
        this.districtList = districtList;
    }
}
