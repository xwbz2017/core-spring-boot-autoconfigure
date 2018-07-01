package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONArray;

/**
 * 实体列表返回
 *
 * @author 花开
 * @date 2018/2/5
 */
public class ListEntityResult extends BaseResult {

    /**
     * 本次检索总结果条数
     */
    private int total;

    /**
     * 本页返回的结果条数
     */
    private int size;

    /**
     * entity详细信息列表，其中返回有column-key自定义字段，所以未转对应实体，以map形式返回
     */
    private JSONArray entities;

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
}
