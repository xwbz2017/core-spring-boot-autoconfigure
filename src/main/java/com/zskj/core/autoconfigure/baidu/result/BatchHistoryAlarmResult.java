package com.zskj.core.autoconfigure.baidu.result;

/**
 * 批量查询所有围栏报警信息
 *
 * @author 花开
 * @date 2018/2/26
 */
public class BatchHistoryAlarmResult extends HistoryAlarmResult {

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
