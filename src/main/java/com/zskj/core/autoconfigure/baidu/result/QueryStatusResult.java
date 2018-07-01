package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 查询监控对象相对围栏的状态
 *
 * @author 花开
 * @date 2018/2/26
 */
public class QueryStatusResult extends BaseResult{

    private int size;

    @JSONField(name = "monitored_statuses")
    private List<String> monitoredStatuses;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<String> getMonitoredStatuses() {
        return monitoredStatuses;
    }

    public void setMonitoredStatuses(List<String> monitoredStatuses) {
        this.monitoredStatuses = monitoredStatuses;
    }

    private class monitoredStatus {
        @JSONField(name = "fence_id")
        private int fenceId;
        @JSONField(name = "monitored_status")
        private String monitoredStatus;

        public int getFenceId() {
            return fenceId;
        }

        public void setFenceId(int fenceId) {
            this.fenceId = fenceId;
        }

        public String getMonitoredStatus() {
            return monitoredStatus;
        }

        public void setMonitoredStatus(String monitoredStatus) {
            this.monitoredStatus = monitoredStatus;
        }
    }
}
