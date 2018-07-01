package com.zskj.core.autoconfigure.baidu;

import com.alibaba.fastjson.JSON;
import com.zskj.core.autoconfigure.baidu.constant.ParamConsts;
import com.zskj.core.autoconfigure.baidu.constant.UrlConsts;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;
import com.zskj.core.autoconfigure.baidu.enums.SupplementMode;
import com.zskj.core.autoconfigure.baidu.param.*;
import com.zskj.core.autoconfigure.baidu.result.*;
import com.zskj.core.autoconfigure.http.HttpClientCore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度鹰眼api
 * 具体接口参数参考文档：http://lbsyun.baidu.com/index.php
 *
 * @author 花开
 * @date 2018/2/2
 */
public class YingYanCore {


    @Autowired
    private HttpClientCore httpClientCore;
    private BaiduProperties properties;

    public YingYanCore(HttpClientCore httpClientCore, BaiduProperties properties) {
        this.httpClientCore = httpClientCore;
        this.properties = properties;
    }

    /**
     * add——添加entity
     * 添加一个新的entity。一个entity代表现实中的一个终端用户，可以是一个人、车或任何运动的物体。
     *
     * @param serviceId  在轨迹管理台创建鹰眼服务时，系统返回的 service_id
     * @param entityName 同一service服务中entity_name不可重复。一旦创建，entity_name 不可更新。
     *                   命名规则：仅支持中文、英文大小字母、英文下划线"_"、英文横线"-"和数字。 entity_name 和 entity_desc 支持联合模糊检索。
     * @param entityDesc 命名规则：仅支持中文、英文大小字母、英文下划线"_"、英文横线"-"和数字。entity_name 和 entity_desc 支持联合模糊检索。
     * @param columnKeys 开发者自定义字段
     * @return 结果响应
     */
    public @CheckForNull
    BaseResult addEntity(int serviceId, @Nonnull String entityName, @Nullable String entityDesc,
                         @Nullable Map<String, Object> columnKeys) {
        AddUpdateEntityParam addUpdateEntityParam = new AddUpdateEntityParam.Builder(serviceId, entityName)
                .entityDesc(entityDesc).columnKeys(columnKeys).build();
        return addEntity(addUpdateEntityParam);
    }

    public @CheckForNull
    BaseResult addEntity(@Nonnull AddUpdateEntityParam addUpdateEntityParam) {
        String result = httpClientCore.doPostForm(UrlConsts.ADD_ENTITY, null, addUpdateEntityParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 更新 entity 的属性信息，如 entity 的描述、entity自定义属性字段的值。
     *
     * @param serviceId  service的ID，作为其唯一标识
     * @param entityName entity名称，作为其唯一标识 	不可更新
     * @param entityDesc entity 可读性描述
     * @param columnKeys 开发者自定义字段
     * @return 结果响应
     */
    public @CheckForNull
    BaseResult updateEntity(int serviceId, @Nonnull String entityName, @Nullable String entityDesc,
                            @Nullable Map<String, Object> columnKeys) {
        AddUpdateEntityParam addUpdateEntityParam = new AddUpdateEntityParam.Builder(serviceId, entityName)
                .entityDesc(entityDesc).columnKeys(columnKeys).build();
        return updateEntity(addUpdateEntityParam);
    }

    public @CheckForNull
    BaseResult updateEntity(@Nonnull AddUpdateEntityParam addUpdateEntityParam) {
        String result = httpClientCore.doPostForm(UrlConsts.UPDATE_ENTITY, null, addUpdateEntityParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 根据entity_name删除一个entity。
     *
     * @param serviceId  service的ID，service 的唯一标识
     * @param entityName entity名称，作为其唯一标识。
     * @return 结果响应
     */
    public @CheckForNull
    BaseResult deleteEntity(int serviceId, @Nonnull String entityName) {
        DeleteEntityParam deleteEntityParam = new DeleteEntityParam.Builder(serviceId, entityName).build();
        return deleteEntity(deleteEntityParam);
    }

    public @CheckForNull
    BaseResult deleteEntity(@Nonnull DeleteEntityParam deleteEntityParam) {
        String result = httpClientCore.doPostForm(UrlConsts.DELETE_ENTITY, null, deleteEntityParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 根据entity_name、自定义字段或活跃时间，查询符合条件的entity
     *
     * @param serviceId       该track所属的service服务的唯一标识
     * @param filter          过滤条件
     * @param coordTypeOutput 返回结果的坐标类型 传空默认值为：bd09ll
     * @param pageNumber      分页索引
     * @param pageSize        分页大小
     * @return entity列表
     */
    public @CheckForNull
    ListEntityResult listEntity(int serviceId, @Nullable String filter, @Nullable CoordType coordTypeOutput,
                                @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        ListEntityParam listEntityParam = new ListEntityParam.Builder(serviceId)
                .filter(filter).coordTypeOutput(coordTypeOutput).pageNumber(pageNumber).pageSize(pageSize).build();
        return listEntity(listEntityParam);
    }

    public @CheckForNull
    ListEntityResult listEntity(@Nonnull ListEntityParam listEntityParam) {
        String result = httpClientCore.doGet(UrlConsts.LIST_ENTITY, null, listEntityParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, ListEntityResult.class);
    }

    /**
     * 根据关键字搜索entity，并返回实时位置
     *
     * @param serviceId       service的ID，service 的唯一标识
     * @param query           搜索关键字
     * @param filter          过滤条件
     * @param sortby          排序方法
     * @param coordTypeOutput 返回结果的坐标类型
     * @param pageNumber      分页索引
     * @param pageSize        分页大小
     * @return entity列表
     * <p>
     * 具体参数见文档：http://lbsyun.baidu.com/index.php?title=yingyan/api/v3/entitysearch
     * </p>
     */
    public @CheckForNull
    SearchResult search(int serviceId, @Nullable String query, @Nullable String filter,
                        @Nullable String sortby, @Nullable CoordType coordTypeOutput,
                        @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        SearchParam searchParam = new SearchParam.Builder(serviceId)
                .query(query).filter(filter).sortby(sortby).coordTypeOutput(coordTypeOutput)
                .pageNumber(pageNumber).pageSize(pageSize)
                .build();
        return search(searchParam);
    }

    public @CheckForNull
    SearchResult search(@Nonnull SearchParam searchParam) {
        String result = httpClientCore.doGet(UrlConsts.SEARCH, null, searchParam.getParamMap(properties.getAk()));
        return JSON.parseObject(result, SearchResult.class);
    }

    /**
     * 根据矩形地理范围搜索entity，并返回实时位置
     *
     * @param serviceId service的ID，service 的唯一标识。
     * @param bounds 矩形区域, 左下角和右上角的经纬度坐标点。
     * @param filter 过滤条件
     * @param sortby 排序方法
     * @param coordTypeInput 请求参数 bounds 的坐标类型
     * @param coordTypeOutput 返回结果的坐标类型
     * @param pageNumber 分页索引
     * @param pageSize 分页大小
     * @return SearchResult
     */
    public @CheckForNull
    SearchResult boundSearch(int serviceId, @Nonnull String bounds,
                             @Nullable String filter,
                             @Nullable String sortby, @Nullable CoordType coordTypeInput,
                             @Nullable CoordType coordTypeOutput,
                             @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        BoundSearchParam boundSearchParam = new BoundSearchParam.Builder(serviceId)
                .bounds(bounds).filter(filter).sortby(sortby).coordTypeInput(coordTypeInput)
                .coordTypeOutput(coordTypeOutput).pageNumber(pageNumber).pageSize(pageSize).build();
        return boundSearch(boundSearchParam);
    }

    public @CheckForNull
    SearchResult boundSearch(@Nonnull BoundSearchParam boundSearchParam) {
        String result = httpClientCore.doGet(UrlConsts.BOUND_SEARCH, null, boundSearchParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, SearchResult.class);
    }

    /**
     * 根据圆心半径搜索entity，并返回实时位置
     *
     * @param serviceId
     * @param center
     * @param radius
     * @param filter
     * @param sortby
     * @param coordTypeInput
     * @param coordTypeOutput
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public @CheckForNull
    SearchResult aroundSearch(int serviceId, @Nonnull String center, int radius,
                              @Nullable String filter,
                              @Nullable String sortby, @Nullable CoordType coordTypeInput,
                              @Nullable CoordType coordTypeOutput,
                              @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        AroundSearchParam aroundSearchParam = new AroundSearchParam.Builder(serviceId, center, radius)
                .filter(filter).sortby(sortby).coordTypeInput(coordTypeInput).coordTypeOutput(coordTypeOutput)
                .pageNumber(pageNumber).pageSize(pageSize).build();
        return aroundSearch(aroundSearchParam);
    }

    public @CheckForNull
    SearchResult aroundSearch(@Nonnull AroundSearchParam aroundSearchParam) {
        String result = httpClientCore.doGet(UrlConsts.AROUND_SEARCH, null, aroundSearchParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, SearchResult.class);
    }

    /**
     * 搜索多边形范围内的entity
     *
     * @param serviceId
     * @param vertexes
     * @param filter
     * @param sortby
     * @param coordTypeInput
     * @param coordTypeOutput
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public @CheckForNull
    SearchResult polygonSearch(int serviceId, @Nonnull String vertexes, @Nullable String filter,
                               @Nullable String sortby, @Nullable CoordType coordTypeInput,
                               @Nullable CoordType coordTypeOutput,
                               @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        PolygonSearchParam polygonSearchParam = new PolygonSearchParam.Builder(serviceId, vertexes)
                .filter(filter).sortby(sortby).coordTypeInput(coordTypeInput).coordTypeOutput(coordTypeOutput)
                .pageNumber(pageNumber).pageSize(pageSize)
                .build();
        return polygonSearch(polygonSearchParam);
    }

    public @CheckForNull
    SearchResult polygonSearch(@Nonnull PolygonSearchParam polygonSearchParam) {
        String result = httpClientCore.doGet(UrlConsts.POLYGON_SEARCH, null, polygonSearchParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, SearchResult.class);
    }

    /**
     * 搜索中国范围内，国家、省、市、区/县中的entity
     *
     * @param serviceId
     * @param keyword
     * @param filter
     * @param sortby
     * @param returnType
     * @param coordTypeOutput
     * @param pageNumber
     * @param pageSize
     * @return 返回值根据returnType有所不同
     */
    public @CheckForNull
    SearchResult districtSearch(int serviceId, @Nonnull String keyword, @Nullable String filter,
                                @Nullable String sortby, @Nullable String returnType,
                                @Nullable CoordType coordTypeOutput,
                                @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        DistrictSearchParam districtSearchParam = new DistrictSearchParam.Builder(serviceId, keyword)
                .filter(filter).sortby(sortby).returnType(returnType)
                .coordTypeOutput(coordTypeOutput).pageNumber(pageNumber).pageSize(pageSize).build();
        return districtSearch(districtSearchParam);
    }

    public @CheckForNull
    SearchResult districtSearch(@Nonnull DistrictSearchParam districtSearchParam) {
        String result = httpClientCore.doGet(UrlConsts.DISTRICT_SEARCH, null, districtSearchParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, SearchResult.class);
    }

    /**
     * 为一个entity上传一个轨迹点
     *
     * @param serviceId      servicede ID，作为其唯一标识
     * @param entityName     entity唯一标识
     * @param latitude       纬度
     * @param longitude      经度
     * @param locTime        定位时设备的时间
     * @param coordTypeInput 坐标类型
     * @param speed          速度
     * @param direction      方向
     * @param height         高度
     * @param radius         定位精度，GPS或定位SDK返回的值
     * @param objectName     对象数据名称
     * @param columnKeys     track的自定义字段
     * @return 响应结果
     */
    public @CheckForNull
    BaseResult addPoint(int serviceId, @Nonnull String entityName, double latitude,
                        double longitude, long locTime, @Nonnull CoordType coordTypeInput,
                        @Nullable Double speed, @Nullable Integer direction, @Nullable Double height,
                        @Nullable Double radius, @Nullable String objectName, @Nullable Map<String, Object> columnKeys) {
        AddPointParam addPointParam = new AddPointParam.Builder(serviceId, entityName, latitude, longitude, locTime, coordTypeInput)
                .speed(speed).direction(direction).height(height)
                .radius(radius).objectName(objectName).columnKeys(columnKeys).build();
        return addPoint(addPointParam);
    }

    public @CheckForNull
    BaseResult addPoint(@Nonnull AddPointParam addPointParam) {
        String result = httpClientCore.doPostForm(UrlConsts.ADD_POINT, null, addPointParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 一次上传多个轨迹点.可上传一个 entity 的多个轨迹点，或多个entity的多个轨迹点，并且可以携带自定义字段的信息。
     *
     * @param serviceId service唯一标识
     * @param pointList 轨迹点总数不超过100个，json 格式。轨迹点字段描述参见 addpoint 接口，其中 entity_name,latitude,longitude,loc_time,coord_type5个字段必填，其他字段可选
     * @return AddPointsResult
     */
    public @CheckForNull
    AddPointsResult addPoints(int serviceId, @Nonnull String pointList) {
        AddPointsParam addPointsParam = new AddPointsParam.Builder(serviceId, pointList).build();
        return addPoints(addPointsParam);
    }

    public @CheckForNull
    AddPointsResult addPoints(@Nonnull AddPointsParam addPointsParam) {
        String result = httpClientCore.doPostForm(UrlConsts.ADD_POINTS, null, addPointsParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, AddPointsResult.class);
    }

    /**
     * 查找某 entity纠偏后的实时位置，以及所在道路的限速信息。适用于持续追踪某一终端，展示纠偏后的实时位置，并实时判断车辆是否超速。
     *
     * @param serviceId       service唯一标识
     * @param entityName      entity唯一标识
     * @param processOption   纠偏选项
     * @param coordTypeOutput 返回的坐标类型
     * @return GetLatestPointResult
     */
    public @CheckForNull
    GetLatestPointResult getLatestPoint(int serviceId, @Nonnull String entityName,
                                        @Nullable String processOption, @Nullable CoordType coordTypeOutput) {
        GetLatestPointParam getLatestPointParam = new GetLatestPointParam.Builder(serviceId, entityName)
                .processOption(processOption).coordTypeOutput(coordTypeOutput).build();
        return getLatestPoint(getLatestPointParam);
    }

    public @CheckForNull
    GetLatestPointResult getLatestPoint(GetLatestPointParam getLatestPointParam) {
        String result = httpClientCore.doGet(UrlConsts.GET_LATEST_POINT, null, getLatestPointParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, GetLatestPointResult.class);
    }

    /**
     * 查询某 entity 一段时间内行驶里程，可应用于无需查询轨迹仅查询里程的场景，如：用车实时计费,支持：
     * 1. 计算总里程，支持计算纠偏后里程
     * 2. 对于中断或丢失的轨迹区间进行补偿，支持使用直线或驾车/骑行/步行路线规划的里程进行补偿
     *
     * @param serviceId      service唯一标识
     * @param entityName     entity唯一标识
     * @param startTime      开始时间 UNIX 时间戳
     * @param endTime        结束时间 UNIX时间戳
     * @param isProcessed    是否返回纠偏后里程
     * @param processOption  纠偏选项
     * @param supplementMode 里程补偿方式
     * @return GetDistanceResult
     */
    public @CheckForNull
    GetDistanceResult getDistance(int serviceId, @Nullable String entityName, long startTime, long endTime,
                                  @Nullable String isProcessed, @Nullable String processOption, @Nullable SupplementMode supplementMode) {
        GetDistanceParam getDistanceParam = new GetDistanceParam.Builder(serviceId, entityName, startTime, endTime)
                .isProcessed(isProcessed).processOption(processOption).supplementMode(supplementMode).build();
        return getDistance(getDistanceParam);
    }

    public @CheckForNull
    GetDistanceResult getDistance(GetDistanceParam getDistanceParam) {
        String result = httpClientCore.doGet(UrlConsts.GET_DISTANCE, null, getDistanceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, GetDistanceResult.class);
    }

    /**
     * 实时查询某entity一段时间内的行程信息：起终点、总里程、收费里程、轨迹点列表等。 支持进行轨迹去噪、抽稀、绑路、里程补偿等
     *
     * @param serviceId  service唯一标识
     * @param entityName entity唯一标识
     * @param startTime  起始时间（起始的loc_time） 	UNIX时间戳
     * @param endTime    结束时间（结束的loc_time） 	UNIX时间戳
     * @return GetTrackResult
     */
    public @CheckForNull
    GetTrackResult getTrack(int serviceId, @Nullable String entityName, long startTime, long endTime) {
        GetTrackParam getTrackParam = new GetTrackParam.Builder(serviceId, entityName, startTime, endTime).build();
        return getTrack(getTrackParam);
    }

    public GetTrackResult getTrack(GetTrackParam getTrackParam) {
        String result = httpClientCore.doGet(UrlConsts.GET_TRACK, null, getTrackParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, GetTrackResult.class);
    }

    /**
     * 查询entity在指定时间段内的停留点。停留点判断规则为：在stay_radius半径范围内，滞留start_time以上，被认为是一次停留，将取一个代表性坐标作为停留点，其中 stay_radius 默认为20米，start_time 默认为 600秒。
     *
     * @param serviceId  service的ID，作为其唯一标识
     * @param entityName entity名称，作为其唯一标识。
     * @param startTime  起始时间（起始的loc_time） 	UNIX时间戳
     * @param endTime    结束时间（结束的loc_time） 	UNIX时间戳
     * @return StayPointResult
     */
    public @CheckForNull
    StayPointResult stayPoint(int serviceId, @Nullable String entityName, long startTime, long endTime) {
        StayPointParam stayPointParam = new StayPointParam.Builder(serviceId, entityName, startTime, endTime).build();
        return stayPoint(stayPointParam);
    }

    public @CheckForNull
    StayPointResult stayPoint(StayPointParam stayPointParam) {
        String result = httpClientCore.doGet(UrlConsts.STAY_POINT, null, stayPointParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, StayPointResult.class);
    }

    /**
     * 查询entity在指定时间段内的驾驶行为，返回以下分析结果：
     * <p>
     * 1. 总体信息：起终点信息、里程、耗时、平均速度、最高速度
     * <p>
     * 2. 异常信息：超速、急加速、急刹车、急转弯
     *
     * @param serviceId  service的ID，作为其唯一标识
     * @param entityName entity名称，作为其唯一标识。
     * @param startTime  起始时间（起始的loc_time） 	UNIX时间戳
     * @param endTime    结束时间（结束的loc_time） 	UNIX时间戳
     * @return DrivingBehaviorResult
     */
    public @CheckForNull
    DrivingBehaviorResult drivingBehavior(int serviceId, @Nullable String entityName, long startTime, long endTime) {
        DrivingBehaviorParam drivingBehaviorParam = new DrivingBehaviorParam.Builder(serviceId, entityName, startTime, endTime).build();
        return drivingBehavior(drivingBehaviorParam);
    }

    public @CheckForNull
    DrivingBehaviorResult drivingBehavior(DrivingBehaviorParam drivingBehaviorParam) {
        String result = httpClientCore.doGet(UrlConsts.DRIVING_BEHAVIOUR, null, drivingBehaviorParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, DrivingBehaviorResult.class);
    }

    /**
     * 创建圆形围栏
     * 以中心点和半径创建一个圆形围栏。
     * 支持两种创建方法：
     * 1、针对确定的某个entity创建围栏，该围栏仅监控1个entity
     * 2、针对某个service下的所有entity创建围栏，该围栏同时监控这个service的所有entity
     *
     * @param serviceId service的唯一标识
     * @param longitude 围栏圆心经度
     * @param latitude  围栏圆心纬度
     * @param radius    围栏半径
     * @param coordType 坐标类型
     * @return CreateFenceResult
     */
    public @CheckForNull
    CreateFenceResult createCircleFence(int serviceId, double longitude, double latitude, double radius, CoordType coordType) {
        CreateCircleFenceParam createCircleFenceParam = new CreateCircleFenceParam.Builder(serviceId, longitude, latitude, radius, coordType).build();
        return createCircleFence(createCircleFenceParam);
    }

    public @CheckForNull
    CreateFenceResult createCircleFence(CreateCircleFenceParam createCircleFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.CREATE_CIRCLE_FENCE, null, createCircleFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, CreateFenceResult.class);
    }

    /**
     * 创建多边形围栏
     * 以多边形形状点创建多边形围栏。
     * 支持两种创建方法：
     * 1、针对确定的某个entity创建围栏，该围栏仅监控1个entity
     * 2、针对某个service下的所有entity创建围栏，该围栏同时监控这个service的所有entity
     *
     * @param serviceId service的唯一标识
     * @param vertexes  多边形围栏形状点
     * @param coordType 坐标类型
     * @return CreateFenceResult
     */
    public @CheckForNull
    CreateFenceResult createPolygonFence(int serviceId, String vertexes, CoordType coordType) {
        CreatePolygonFenceParam createPolygonFenceParam = new CreatePolygonFenceParam.Builder(serviceId, vertexes, coordType).build();
        return createPolygonFence(createPolygonFenceParam);
    }

    public @CheckForNull
    CreateFenceResult createPolygonFence(CreatePolygonFenceParam createPolygonFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.CREATE_POLYGON_FENCE, null, createPolygonFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, CreateFenceResult.class);
    }

    /**
     * 创建线型围栏
     * 以路线形状点创建线型围栏。
     * 支持两种创建方法：
     * 1、针对确定的某个entity创建围栏，该围栏仅监控1个entity
     * 2、针对某个service下的所有entity创建围栏，该围栏同时监控这个service的所有entity
     *
     * @param serviceId service的唯一标识
     * @param vertexes  线型围栏坐标点
     * @param offset    偏离距离
     * @param coordType 坐标类型
     * @return CreateFenceResult
     */
    public @CheckForNull
    CreateFenceResult createPolylineFence(int serviceId, String vertexes, int offset, CoordType coordType) {
        CreatePolylineFenceParam createPolylineFenceParam = new CreatePolylineFenceParam.Builder(serviceId, vertexes, offset, coordType).build();
        return createPolylineFence(createPolylineFenceParam);
    }

    public @CheckForNull
    CreateFenceResult createPolylineFence(CreatePolylineFenceParam createPolylineFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.CREATE_POLYLINE_FENCE, null, createPolylineFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, CreateFenceResult.class);
    }

    /**
     * 创建行政区划围栏
     * 以行政区划关键字创建围栏。
     * 1. 若关键字匹配至唯一的行政区划，则将创建该围栏
     * 2. 若关键字匹配至多个行政区划，则围栏创建失败，将返回匹配的行政区划名称列表
     * <p>
     * 支持两种创建方法：
     * 1、针对确定的某个entity创建围栏，该围栏仅监控1个entity
     * 2、针对某个service下的所有entity创建围栏，该围栏同时监控这个service的所有entity
     *
     * @param serviceId 该围栏实体所属的轨迹服务ID
     * @param keyword   行政区划关键字
     * @return CreateDistrictFenceResult
     */
    public @CheckForNull
    CreateDistrictFenceResult createDistrictFence(int serviceId, String keyword) {
        CreateDistrictFenceParam createDistrictFenceParam = new CreateDistrictFenceParam.Builder(serviceId, keyword).build();
        return createDistrictFence(createDistrictFenceParam);
    }

    public @CheckForNull
    CreateDistrictFenceResult createDistrictFence(CreateDistrictFenceParam createDistrictFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.CREATE_DISTRICT_FENCE, null, createDistrictFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, CreateDistrictFenceResult.class);
    }

    /**
     * 更新圆形围栏
     *
     * @param serviceId service的唯一标识
     * @param fenceId   围栏的唯一标识
     * @param longitude 围栏圆心经度
     * @param latitude  围栏圆心纬度
     * @param radius    围栏半径
     * @param coordType 坐标类型
     * @return BaseResult
     */
    public @CheckForNull
    BaseResult updateCircleFence(int serviceId, int fenceId, Double longitude, Double latitude, Double radius, CoordType coordType) {
        UpdateCircleFenceParam updateCircleFenceParam = new UpdateCircleFenceParam.Builder(serviceId, fenceId, longitude, latitude, radius, coordType).build();
        return updateCircleFence(updateCircleFenceParam);
    }

    public @CheckForNull
    BaseResult updateCircleFence(UpdateCircleFenceParam updateCircleFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.UPDATE_CIRCLE_FENCE, null, updateCircleFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 更新多边形围栏
     *
     * @param serviceId service的唯一标识
     * @param fenceId   围栏的唯一标识
     * @param vertexes  多边形围栏形状点
     * @param coordType 坐标类型
     * @return BaseResult
     */
    public @CheckForNull
    BaseResult updatePolygonFence(int serviceId, int fenceId, String vertexes, CoordType coordType) {
        UpdatePolygonFenceParam updatePolygonFenceParam = new UpdatePolygonFenceParam.Builder(serviceId, fenceId, vertexes, coordType).build();
        return updatePolygonFence(updatePolygonFenceParam);
    }

    public @CheckForNull
    BaseResult updatePolygonFence(UpdatePolygonFenceParam updatePolygonFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.UPDATE_POLYGON_FENCE, null, updatePolygonFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 更新线型围栏
     *
     * @param serviceId service的唯一标识
     * @param fenceId   围栏的唯一标识
     * @param vertexes  线型围栏坐标点
     * @param offset    偏离距离
     * @param coordType 坐标类型
     * @return BaseResult
     */
    public @CheckForNull
    BaseResult updatePolylineFence(int serviceId, int fenceId, String vertexes, int offset, CoordType coordType) {
        UpdatePolylineFenceParam updatePolylineFenceParam = new UpdatePolylineFenceParam.Builder(serviceId, fenceId, vertexes, offset, coordType).build();
        return updatePolylineFence(updatePolylineFenceParam);
    }

    public @CheckForNull
    BaseResult updatePolylineFence(UpdatePolylineFenceParam updatePolylineFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.UPDATE_POLYLINE_FENCE, null, updatePolylineFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 更新行政区划围栏
     *
     * @param serviceId service的唯一标识
     * @param fenceId   围栏的唯一标识
     * @param keyword   行政区划关键字
     * @return BaseResult
     */
    public @CheckForNull
    CreateDistrictFenceResult updateDistrictFence(int serviceId, int fenceId, String keyword) {
        UpdateDistrictFenceParam updateDistrictFenceParam = new UpdateDistrictFenceParam.Builder(serviceId, fenceId, keyword).build();
        return updateDistrictFence(updateDistrictFenceParam);
    }

    public @CheckForNull
    CreateDistrictFenceResult updateDistrictFence(UpdateDistrictFenceParam updateDistrictFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.UPDATE_DISTRICT_FENCE, null, updateDistrictFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, CreateDistrictFenceResult.class);
    }

    /**
     * 删除围栏
     *
     * @param serviceId       service的唯一标识
     * @param monitoredPerson 监控对象
     * @param fenceIds        围栏id列表
     * @return DeleteFenceResult
     */
    public @CheckForNull
    DeleteFenceResult deleteFence(int serviceId, String monitoredPerson, String fenceIds) {
        DeleteFenceParam deleteFenceParam = new DeleteFenceParam.Builder(serviceId, monitoredPerson, fenceIds).build();
        return deleteFence(deleteFenceParam);
    }

    public @CheckForNull
    DeleteFenceResult deleteFence(DeleteFenceParam deleteFenceParam) {
        String result = httpClientCore.doPostForm(UrlConsts.DELETE_FENCE, null, deleteFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, DeleteFenceResult.class);
    }

    /**
     * 查询围栏
     *
     * @param serviceId       service的唯一标识
     * @param monitoredPerson 监控对象
     * @param fenceIds        围栏id列表
     * @return ListFenceParam
     */
    public @CheckForNull
    ListFenceResult listFence(int serviceId, String monitoredPerson, String fenceIds) {
        ListFenceParam listFenceParam = new ListFenceParam.Builder(serviceId, monitoredPerson, fenceIds).build();
        return listFence(listFenceParam);
    }

    public @CheckForNull
    ListFenceResult listFence(ListFenceParam listFenceParam) {
        String result = httpClientCore.doGet(UrlConsts.LIST_FENCE, null, listFenceParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, ListFenceResult.class);
    }

    /**
     * 针对某一个地理围栏增加entity
     *
     * @param serviceId       该围栏实体所属的轨迹服务ID
     * @param fenceId         围栏的唯一标识
     * @param monitoredPerson 监控对象
     * @return BaseResult
     */
    public @CheckForNull
    BaseResult addMonitoredPerson(int serviceId, int fenceId, String monitoredPerson) {
        AddMonitoredPersonParam addMonitoredPersonParam = new AddMonitoredPersonParam.Builder(serviceId, fenceId, monitoredPerson).build();
        return addMonitoredPerson(addMonitoredPersonParam);
    }

    public @CheckForNull
    BaseResult addMonitoredPerson(AddMonitoredPersonParam addMonitoredPersonParam) {
        String result = httpClientCore.doPostForm(UrlConsts.ADD_MONITORED_PERSON, null, addMonitoredPersonParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, ListFenceResult.class);
    }

    /**
     * 删除围栏可去除监控的entity
     *
     * @param serviceId       service的唯一标识
     * @param fenceId         围栏id
     * @param monitoredPerson 监控对象
     * @return BaseResult
     */
    public @CheckForNull
    BaseResult deleteMonitoredPerson(int serviceId, int fenceId, String monitoredPerson) {
        Map<String, Object> params = new HashMap<>(4);
        params.put(ParamConsts.AK, properties.getAk());
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.FENCE_ID, fenceId);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        String result = httpClientCore.doPostForm(UrlConsts.DELETE_MONITORED_PERSON, null, params);
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 查询某service下的某一个围栏下的所有entity，方便开发者管理查询entity
     *
     * @param serviceId  service的唯一标识
     * @param fenceId    围栏id
     * @param pageNumber 分页索引
     * @param pageSize   分页大小
     * @return ListMonitoredPersonResult
     */
    public @CheckForNull
    ListMonitoredPersonResult listMonitoredPerson(int serviceId, int fenceId, Integer pageNumber, Integer pageSize) {
        Map<String, Object> params = new HashMap<>(5);
        params.put(ParamConsts.AK, properties.getAk());
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.FENCE_ID, fenceId);
        params.put(ParamConsts.PAGE_INDEX, pageNumber);
        params.put(ParamConsts.PAGE_SIZE, pageSize);
        String result = httpClientCore.doGet(UrlConsts.LIST_MONITORED_PERSON, null, params);
        return JSON.parseObject(result, ListMonitoredPersonResult.class);
    }

    /**
     * 查询被监控对象在指定围栏内或外，也支持查询被监控对象目前相对于所有围栏的状态
     *
     * @param serviceId       service的ID，service 的唯一标识。
     * @param monitoredPerson 监控对象的 entity_name
     * @param fenceIds        围栏实体的id列表
     * @return QueryStatusResult
     */
    public @CheckForNull
    QueryStatusResult queryStatus(int serviceId, @Nonnull String monitoredPerson, @Nullable String fenceIds) {
        Map<String, Object> params = new HashMap<>(4);
        params.put(ParamConsts.AK, properties.getAk());
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.MONITORED_PERSON, monitoredPerson);
        params.put(ParamConsts.FENCE_IDS, fenceIds);
        String result = httpClientCore.doGet(UrlConsts.QUERY_STATUS, null, params);
        return JSON.parseObject(result, QueryStatusResult.class);
    }

    /**
     * 查询某监控对象的围栏报警信息
     * 查询围栏的监控对象7天以内（包含7天）的围栏报警信息，7天以外的报警信息将被删除。
     * <p>
     * 注：即使围栏或监控对象已被删除，仍能根据 fence_id 和 monitored_person 查询7天之内的报警信息。
     *
     * @param serviceId       service的ID，service 的唯一标识。
     * @param monitoredPerson 监控对象的 entity_name
     * @return HistoryAlarmResult
     */
    public @CheckForNull
    HistoryAlarmResult historyalarm(int serviceId, String monitoredPerson) {
        HistoryAlarmParam historyAlarmParam = new HistoryAlarmParam.Builder(serviceId, monitoredPerson).build();
        return historyAlarm(historyAlarmParam);
    }

    public @CheckForNull
    HistoryAlarmResult historyAlarm(HistoryAlarmParam historyAlarmParam) {
        String result = httpClientCore.doGet(UrlConsts.HISTORY_ALARM, null, historyAlarmParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, HistoryAlarmResult.class);
    }

    /**
     * 批量查询所有围栏报警信息
     * 批量查询某 service 7天内任意1小时，所有围栏的报警信息
     *
     * @param serviceId service的ID，service 的唯一标识。
     * @param startTime 查询的时间是服务端接收到报警的时间，即报警信息的 create_time。例如，轨迹点实际触发围栏时间为13:00，但若由于各种原因，轨迹点上传至服务端进行围栏计算的时间为14:00，则该报警的 create_time为14:00。
     * @param endTime   结束时间需大于开始时间，但不可超过1小时。即每次请求，最多只能同步1个小时时长的报警信息。
     * @return BatchHistoryAlarmResult
     */
    public @CheckForNull
    BatchHistoryAlarmResult batchHistoryAlarm(int serviceId, long startTime, long endTime) {
        BatchHistoryAlarmParam batchHistoryAlarmParam = new BatchHistoryAlarmParam.Builder(serviceId, startTime, endTime).build();
        return batchHistoryAlarm(batchHistoryAlarmParam);
    }

    public @CheckForNull
    BatchHistoryAlarmResult batchHistoryAlarm(BatchHistoryAlarmParam batchHistoryAlarmParam) {
        String result = httpClientCore.doGet(UrlConsts.BATCH_HISTORY_ALARM, null, batchHistoryAlarmParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, BatchHistoryAlarmResult.class);
    }

    /**
     * 创建任务
     * 创建一个新的轨迹数据导出任务，下载该service 内一段时间内的全部轨迹。
     * 注意：
     * 1. 只能下载距当前时间12小时之前的轨迹，例如：2017-8-7 10:00创建的下载任务只能下载2017-8-6 22:00之前产生的轨迹
     * 2. 每一个任务最多下载24小时时长的轨迹。例如，若下载7天的轨迹，则需创建7个任务
     * <p>
     * 3. 每个service_id同时只允许存在10个未完成任务，超过10个则返回创建失败，请等待现有的任务处理完之后再创建新的任务
     *
     * @param serviceId service的ID，service 的唯一标识。
     * @param startTime 轨迹起始时间 UNIX 时间戳
     * @param endTime   轨迹结束时间 UNIX 时间戳
     * @return CreateJobResult
     */
    public @CheckForNull
    CreateJobResult createJob(int serviceId, long startTime, long endTime) {
        CreateJobParam createJobParam = new CreateJobParam.Builder(serviceId, startTime, endTime).build();
        return createJob(createJobParam);
    }

    public @CheckForNull
    CreateJobResult createJob(CreateJobParam createJobParam) {
        String result = httpClientCore.doPostForm(UrlConsts.CREATE_JOB, null, createJobParam.getParamsMap(properties.getAk()));
        return JSON.parseObject(result, CreateJobResult.class);
    }

    /**
     * 删除任务
     *
     * @param serviceId service的ID，service 的唯一标识。
     * @param jobId     任务id
     * @return BaseResult
     */
    public @CheckForNull
    BaseResult deleteJob(int serviceId, int jobId) {
        Map<String, Object> params = new HashMap<>(3);
        params.put(ParamConsts.AK, properties.getAk());
        params.put(ParamConsts.SERVICE_ID, serviceId);
        params.put(ParamConsts.JOB_ID, jobId);
        String result = httpClientCore.doPostForm(UrlConsts.DELETE_JOB, null, params);
        return JSON.parseObject(result, BaseResult.class);
    }

    /**
     * 查询任务
     * 查询任务池中的任务，任务池中包括以下几类任务：
     * 1. 已创建尚未开始执行的任务
     * 2. 正在执行的任务
     * 3. 已完成的任务，但完成时间不超过48小时（注：已完成的任务会在48小时之后自动清理）
     * <p>
     * 已完成的任务会返回file_url，将地址粘贴至浏览器或使用其他下载方法，即可获得轨迹数据文件。
     *
     * @param serviceId service的ID，service 的唯一标识。
     * @return GetJobResult
     */
    public @CheckForNull
    GetJobResult getJob(int serviceId) {
        Map<String, Object> params = new HashMap<>(2);
        params.put(ParamConsts.AK, properties.getAk());
        params.put(ParamConsts.SERVICE_ID, serviceId);
        String result = httpClientCore.doGet(UrlConsts.GET_JOB, null, params);
        return JSON.parseObject(result, GetJobResult.class);
    }
}
