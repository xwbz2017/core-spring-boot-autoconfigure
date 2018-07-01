package com.zskj.core.autoconfigure.baidu;

import com.alibaba.fastjson.JSONObject;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;
import com.zskj.core.autoconfigure.baidu.param.AddUpdateEntityParam;
import com.zskj.core.autoconfigure.baidu.result.BaseResult;
import com.zskj.core.autoconfigure.baidu.result.GetLatestPointResult;
import com.zskj.core.autoconfigure.baidu.result.ListEntityResult;
import com.zskj.core.autoconfigure.baidu.result.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 鹰眼api测试
 *
 * @author 花开
 * @date 2018/2/6
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class YingYanCoreTest {

    @Autowired
    private YingYanCore core;
    @Autowired
    private BaiduProperties baiduProperties;
    private Logger logger = LoggerFactory.getLogger(YingYanCoreTest.class);

    @Test
    public void addEntity() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        String entityName = "大众汽车008";
        String entityDesc = "这是出租车002号";
        Map<String, Object> columnKeys = new HashMap<>();
        columnKeys.put("phone", "18910002000");
        columnKeys.put("name", "小张师傅");

        // demo1
        AddUpdateEntityParam params = new AddUpdateEntityParam.Builder(serviceId, entityName).entityDesc(entityDesc).columnKeys(columnKeys).build();
        BaseResult baseResult = core.addEntity(params);
        logger.info(JSONObject.toJSONString(baseResult));

        // demo2
        baseResult = core.addEntity(serviceId, entityName, entityDesc, columnKeys);
        logger.info(JSONObject.toJSONString(baseResult));
    }

    @Test
    public void deleteEntity() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        String entityName = "大众汽车008";
        BaseResult baseResult = core.deleteEntity(serviceId, entityName);
        logger.info(JSONObject.toJSONString(baseResult));
    }

    @Test
    public void updateEntity() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        String entityName = "大众汽车008";
        String entityDesc = "备注";
        Map<String, Object> columnKeys = new HashMap<>();
        columnKeys.put("phone", "18910002000");
        columnKeys.put("name", "小张师傅");
        BaseResult baseResult = core.updateEntity(serviceId, entityName, entityDesc, columnKeys);
        logger.info(JSONObject.toJSONString(baseResult));
    }

    @Test
    public void listEntity() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        ListEntityResult listEntityResult = core.listEntity(serviceId, null, null, 1, 200);
        logger.info(JSONObject.toJSONString(listEntityResult));
    }

    @Test
    public void search() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        SearchResult result = core.search(serviceId, "大众", null, null, null , 1,200);
        logger.info(JSONObject.toJSONString(result));
    }

    @Test
    public void boundSearch() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        SearchResult result = core.boundSearch(serviceId, "36.20,116.30;37.20,117.30", null, null, null, null , 1,200);
        logger.info(JSONObject.toJSONString(result));
    }

    @Test
    public void aroundSearch() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        SearchResult result = core.aroundSearch(serviceId, "36.20,116.30", 5000, null, null, null,null, null, null);
        logger.info(JSONObject.toJSONString(result));
    }

    @Test
    public void addPoint() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        String entityName = "大众汽车008";
        BaseResult result = core.addPoint(serviceId, entityName,36.20, 116.30,
                System.currentTimeMillis()/ 1000, CoordType.wgs84, null, null, null, null, null, null);
        logger.info(JSONObject.toJSONString(result));
    }

    @Test
    public void getLatestPoint() throws Exception {
        int serviceId = baiduProperties.getServiceIds().get(0);
        String entityName = "大众汽车008";
        GetLatestPointResult result = core.getLatestPoint(serviceId, entityName, null, null);
        logger.info(JSONObject.toJSONString(result));
    }
}
