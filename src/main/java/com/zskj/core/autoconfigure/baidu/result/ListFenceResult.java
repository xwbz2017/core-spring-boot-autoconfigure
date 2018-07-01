package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.List;

/**
 * 查询围栏
 *
 * @author 花开
 * @date 2018/2/26
 */
public class ListFenceResult extends BaseResult{

    private int size;

    private List<Fence> fences;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Fence> getFences() {
        return fences;
    }

    public void setFences(List<Fence> fences) {
        this.fences = fences;
    }

    private class Fence {
        @JSONField(name = "fence_id")
        private int fenceId;

        @JSONField(name = "fence_name")
        private String fenceName;

        @JSONField(name = "monitored_person")
        private String monitoredPerson;

        private String shape;
        private Double longitude;
        private Double latitude;
        private Double radius;
        private JSONArray vertexes;
        private Double offset;
        @JSONField(name = "coord_type")
        private CoordType coordType;
        private Integer denoise;
        private String district;
        @JSONField(name = "create_time")
        private String createTime;
        @JSONField(name = "modify_time")
        private String modifyTime;

        public int getFenceId() {
            return fenceId;
        }

        public void setFenceId(int fenceId) {
            this.fenceId = fenceId;
        }

        public String getFenceName() {
            return fenceName;
        }

        public void setFenceName(String fenceName) {
            this.fenceName = fenceName;
        }

        public String getMonitoredPerson() {
            return monitoredPerson;
        }

        public void setMonitoredPerson(String monitoredPerson) {
            this.monitoredPerson = monitoredPerson;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getRadius() {
            return radius;
        }

        public void setRadius(Double radius) {
            this.radius = radius;
        }

        public JSONArray getVertexes() {
            return vertexes;
        }

        public void setVertexes(JSONArray vertexes) {
            this.vertexes = vertexes;
        }

        public Double getOffset() {
            return offset;
        }

        public void setOffset(Double offset) {
            this.offset = offset;
        }

        public CoordType getCoordType() {
            return coordType;
        }

        public void setCoordType(CoordType coordType) {
            this.coordType = coordType;
        }

        public Integer getDenoise() {
            return denoise;
        }

        public void setDenoise(Integer denoise) {
            this.denoise = denoise;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
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
    }
}
