package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 创建行政区划围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class CreateDistrictFenceResult extends CreateFenceResult {

    /**
     * status=0，围栏创建成功时返回该字段
     */
    private String district;

    /**
     * status=5108：围栏创建失败，关键字匹配至多个行政区时，返回该字段
     */
    @JSONField(name = "district_list")
    private List<String> districtList;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<String> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<String> districtList) {
        this.districtList = districtList;
    }
}
