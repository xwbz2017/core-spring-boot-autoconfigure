package com.zskj.core.autoconfigure.baidu;

import com.zskj.core.autoconfigure.baidu.enums.SignType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 百度api配置属性
 *
 * @author 花开
 * @date 2018/2/2
 */
@ConfigurationProperties(prefix = BaiduProperties.PREFIX)
public class BaiduProperties {

    static final String PREFIX = "core.baidu";

    /**
     * 自动化配置开关
     */
    private boolean mapEnabled;

    /**
     * 用户的ak
     */
    private String ak;

    /** 验证方式 */
    private String signType = SignType.IP.name();

    /**
     * 在轨迹管理台创建鹰眼服务时，系统返回的 service_id
     */
    private List<Integer> serviceIds = new ArrayList<>();

    public boolean isMapEnabled() {
        return mapEnabled;
    }

    public void setMapEnabled(boolean mapEnabled) {
        this.mapEnabled = mapEnabled;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public List<Integer> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Integer> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
