package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 删除围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class DeleteFenceResult extends BaseResult {

    @JSONField(name = "fence_ids")
    private List<Integer> fenceIds;

    public List<Integer> getFenceIds() {
        return fenceIds;
    }

    public void setFenceIds(List<Integer> fenceIds) {
        this.fenceIds = fenceIds;
    }
}
