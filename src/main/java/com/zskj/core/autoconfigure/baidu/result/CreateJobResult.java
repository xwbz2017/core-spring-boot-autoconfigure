package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 创建一个任务，该任务完成后将返回文件下载地址，供开发者下载
 *
 * @author 花开
 * @date 2018/2/26
 */
public class CreateJobResult extends BaseResult{

    @JSONField(name = "job_id")
    private int jobId;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
