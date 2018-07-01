package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.List;

/**
 * 查询任务
 *
 * @author 花开
 * @date 2018/2/26
 */
public class GetJobResult extends BaseResult{

    private int total;

    private List<Job> jobs;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    private class Job {
        @JSONField(name = "job_id")
        private int jobId;
        @JSONField(name = "start_time")
        private long startTime;
        @JSONField(name = "end_time")
        private long endTime;
        @JSONField(name = "coord_type_output")
        private CoordType coordTypeOutput;
        @JSONField(name = "create_time")
        private String createTime;
        @JSONField(name = "modify_time")
        private String modifyTime;
        @JSONField(name = "job_status")
        private String jobStatus;
        @JSONField(name = "file_url")
        private String fileUrl;

        public int getJobId() {
            return jobId;
        }

        public void setJobId(int jobId) {
            this.jobId = jobId;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public CoordType getCoordTypeOutput() {
            return coordTypeOutput;
        }

        public void setCoordTypeOutput(CoordType coordTypeOutput) {
            this.coordTypeOutput = coordTypeOutput;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getJobStatus() {
            return jobStatus;
        }

        public void setJobStatus(String jobStatus) {
            this.jobStatus = jobStatus;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
}
