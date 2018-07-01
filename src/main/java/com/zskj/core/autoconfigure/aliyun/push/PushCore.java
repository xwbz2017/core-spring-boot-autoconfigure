package com.zskj.core.autoconfigure.aliyun.push;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.push.model.v20160801.*;
import com.aliyuncs.utils.ParameterHelper;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.zskj.core.autoconfigure.aliyun.AliYunProperties;
import com.zskj.core.autoconfigure.aliyun.push.enums.*;
import com.zskj.core.autoconfigure.aliyun.push.model.AndroidAdvancedPushConfig;
import com.zskj.core.autoconfigure.aliyun.push.model.IOSAdvancedPushConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 推送
 *
 * @author 花开
 * @date 2018/2/1
 */
public class PushCore {

    private Logger logger = LoggerFactory.getLogger(PushCore.class);

    private AliYunProperties properties;

    private IAcsClient client;

    public PushCore(IAcsClient client, AliYunProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    /**
     * 推消息给Android设备
     *
     * @param target      推送目标
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。
     *                    Target=DEVICE，值如deviceid111,deviceid1111
     *                    Target=ACCOUNT，值如account111,account222
     *                    Target=ALIAS，值如alias111,alias222
     *                    Target=TAG，支持单Tag和多Tag，格式请参考 标签格式
     *                    Target=ALL，值为all
     * @param title       发送的消息的标题
     * @param body        发送的消息内容
     *                    <p>
     *                    参见文档 https://help.aliyun.com/document_detail/48085.html
     *                    </p>
     * @return messageId 如果为null 说明推送失败
     */
    public String pushMessageToAndroid(@Nonnull Target target, @Nonnull String targetValue, @Nonnull String title, @Nonnull String body) {
        PushMessageToAndroidRequest request = new PushMessageToAndroidRequest();
        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setAppKey(properties.getPushAppKey());
        request.setTarget(target.name());
        request.setTargetValue(targetValue);
        request.setTitle(title);
        request.setBody(body);
        try {
            PushMessageToAndroidResponse response = client.getAcsResponse(request);
            if (logger.isDebugEnabled()) {
                logger.debug("requestId:{}, messageId:{}", response.getRequestId(), response.getMessageId());
            }
            return response.getMessageId();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 推通知给Android设备
     *
     * @param target      推送目标
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。
     *                    Target=DEVICE，值如deviceid111,deviceid1111
     *                    Target=ACCOUNT，值如account111,account222
     *                    Target=ALIAS，值如alias111,alias222
     *                    Target=TAG，支持单Tag和多Tag，格式请参考 标签格式
     *                    Target=ALL，值为all
     * @param title       发送的消息的标题
     * @param body        发送的消息内容
     * @param extParams   自定义的KV结构，供开发者扩展使用，针对Android设备。该参数要以json map的格式传入。
     *                    <p>
     *                    参见文档 https://help.aliyun.com/document_detail/48087.html
     *                    </p>
     * @return messageId 如果为null 说明推送失败
     */
    public String pushNoticeToAndroid(@Nonnull Target target, @Nonnull String targetValue, @Nonnull String title, @Nonnull String body, @Nullable String extParams) {
        PushNoticeToAndroidRequest request = new PushNoticeToAndroidRequest();
        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setAppKey(properties.getPushAppKey());
        request.setTarget(target.name());
        request.setTargetValue(targetValue);
        request.setTitle(title);
        request.setBody(body);
        request.setExtParameters(extParams);
        try {
            PushNoticeToAndroidResponse response = client.getAcsResponse(request);
            if (logger.isDebugEnabled()) {
                logger.debug("requestId:{}, messageId:{}", response.getRequestId(), response.getMessageId());
            }
            return response.getMessageId();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 推消息给IOS设备
     *
     * @param target      推送目标
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。
     *                    Target=DEVICE，值如deviceid111,deviceid1111
     *                    Target=ACCOUNT，值如account111,account222
     *                    Target=ALIAS，值如alias111,alias222
     *                    Target=TAG，支持单Tag和多Tag，格式请参考 标签格式
     *                    Target=ALL，值为all
     * @param title       发送的消息的标题
     * @param body        发送的消息内容
     *                    <p>
     *                    参见文档 https://help.aliyun.com/knowledge_detail/48086.html
     *                    </p>
     * @return messageId 如果为null 说明推送失败
     */
    public String pushMessageToIOS(@Nonnull Target target, @Nonnull String targetValue, @Nonnull String title, @Nonnull String body) {
        PushMessageToiOSRequest request = new PushMessageToiOSRequest();
        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setAppKey(properties.getPushAppKey());
        request.setTarget(target.name());
        request.setTargetValue(targetValue);
        request.setTitle(title);
        request.setBody(body);
        try {
            PushMessageToiOSResponse response = client.getAcsResponse(request);
            if (logger.isDebugEnabled()) {
                logger.debug("requestId:{}, messageId:{}", response.getRequestId(), response.getMessageId());
            }
            return response.getMessageId();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 推通知给Android设备
     *
     * @param target      推送目标
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个。
     *                    Target=DEVICE，值如deviceid111,deviceid1111
     *                    Target=ACCOUNT，值如account111,account222
     *                    Target=ALIAS，值如alias111,alias222
     *                    Target=TAG，支持单Tag和多Tag，格式请参考 标签格式
     *                    Target=ALL，值为all
     * @param title       发送的通知的标题(iOS10以上才会显示，可不填)
     * @param body        发送的消息内容
     * @param apnsEnv     iOS的通知是通过APNS中心来发送的，需要填写对应的环境信息，DEV表示开发环境，PRODUCT表示生产环境
     * @param extParams   自定义的KV结构，供开发者扩展使用，针对Android设备。该参数要以json map的格式传入。
     *                    <p>
     *                    参见文档 https://help.aliyun.com/knowledge_detail/48088.html
     *                    </p>
     * @return messageId 如果为null 说明推送失败
     */
    public String pushNoticeToIOS(@Nonnull Target target, @Nonnull String targetValue, @Nullable String title, @Nonnull String body, @
            Nonnull ApnsEnv apnsEnv, @Nullable String extParams) {
        PushNoticeToiOSRequest request = new PushNoticeToiOSRequest();
        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setAppKey(properties.getPushAppKey());
        request.setApnsEnv(apnsEnv.name());
        request.setTarget(target.name());
        request.setTargetValue(targetValue);
        request.setTitle(title);
        request.setBody(body);
        request.setExtParameters(extParams);
        try {
            PushNoticeToiOSResponse response = client.getAcsResponse(request);
            if (logger.isDebugEnabled()) {
                logger.debug("requestId:{}, messageId:{}", response.getRequestId(), response.getMessageId());
            }
            return response.getMessageId();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 推送到android高级接口
     *
     * @param target      推送目标
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，超过限制需要分多次推送。
     * @param pushType    推送类型
     * @param title       Android推送时通知/消息的标题以及iOS消息的标题（必填），iOS 10+通知显示标题，[ iOS 8.2 <= iOS系统 < iOS 10 ]替换通知应用名称（选填）
     * @param body        Android推送时通知的内容/消息的内容；iOS消息/通知内容，推送的内容大小是有限制的，参照 产品限制
     * @param config      android推送高级配置
     * @return 返回messageId不为空说明成功
     * 参见文档 https://help.aliyun.com/document_detail/48089.html
     */
    public String advancedPushToAndroid(@Nonnull Target target, @Nonnull String targetValue, @Nonnull PushType pushType,
                                        @Nonnull String title, @Nonnull String body, @Nonnull AndroidAdvancedPushConfig config) {
        PushRequest pushRequest = new PushRequest();
        pushRequest.setProtocol(ProtocolType.HTTPS);
        pushRequest.setMethod(MethodType.POST);
        pushRequest.setAppKey(properties.getPushAppKey());
        pushRequest.setTarget(target.name());
        pushRequest.setTargetValue(targetValue);
        pushRequest.setPushType(pushType.name());
        // 推送配置
        pushRequest.setDeviceType(DeviceType.ANDROID.name());
        pushRequest.setTitle(title);
        pushRequest.setBody(body);
        String[] ignoreProperties = new String[]{"pushDate", "expireDate"};
        BeanUtils.copyProperties(config, pushRequest, ignoreProperties);
        // 推送控制
        if (config.getPushDate() != null) {
            pushRequest.setPushTime(ParameterHelper.getISO8601Time(config.getPushDate()));
        }
        if (config.getExpireDate() != null) {
            pushRequest.setExpireTime(ParameterHelper.getISO8601Time(config.getExpireDate()));
        }
        return handlePushRequest(pushRequest);
    }

    /**
     * 推送到iOS高级接口
     *
     * @param target      推送目标
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，超过限制需要分多次推送。
     * @param pushType    推送类型
     * @param title       Android推送时通知/消息的标题以及iOS消息的标题（必填），iOS 10+通知显示标题，[ iOS 8.2 <= iOS系统 < iOS 10 ]替换通知应用名称（选填）
     * @param body        Android推送时通知的内容/消息的内容；iOS消息/通知内容，推送的内容大小是有限制的，参照 产品限制
     * @param config      ios推送高级配置
     * @return 返回messageId不为空说明成功
     * <p>
     * 参见文档 https://help.aliyun.com/document_detail/48089.html
     */
    public String advancedPushToIOS(@Nonnull Target target, @Nonnull String targetValue, @Nonnull PushType pushType,
                                    @Nullable String title, @Nonnull String body, @Nonnull IOSAdvancedPushConfig config) {
        PushRequest pushRequest = new PushRequest();
        pushRequest.setProtocol(ProtocolType.HTTPS);
        pushRequest.setMethod(MethodType.POST);
        pushRequest.setAppKey(properties.getPushAppKey());
        pushRequest.setTarget(target.name());
        pushRequest.setTargetValue(targetValue);
        pushRequest.setPushType(pushType.name());
        // 推送配置
        pushRequest.setDeviceType(DeviceType.iOS.name());
        pushRequest.setTitle(title);
        pushRequest.setBody(body);
        String[] ignoreProperties = new String[]{"pushDate", "expireDate"};
        BeanUtils.copyProperties(config, pushRequest, ignoreProperties);
        // 推送控制
        if (config.getPushDate() != null) {
            pushRequest.setPushTime(ParameterHelper.getISO8601Time(config.getPushDate()));
        }
        if (config.getExpireDate() != null) {
            pushRequest.setExpireTime(ParameterHelper.getISO8601Time(config.getExpireDate()));
        }
        return handlePushRequest(pushRequest);
    }

    private String handlePushRequest(PushRequest pushRequest) {
        try {
            PushResponse response = client.getAcsResponse(pushRequest);
            if (logger.isDebugEnabled()) {
                logger.debug("requestId:{}, messageId:{}", response.getRequestId(), response.getMessageId());
            }
            return response.getMessageId();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 取消某次尚未执行的定时推送任务
     *
     * @param messageId 某次推送任务的MessageId
     * @return 取消成功或失败
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48090.html</p>
     */
    public boolean cancelPush(String messageId) {
        CancelPushRequest request = new CancelPushRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setMessageId(messageId);
        try {
            client.getAcsResponse(request);
            return true;
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return false;

    }

    /**
     * 绑定别名。注意：一次最多只能绑定10个Alias，绑定别名之后立即生效。
     *
     * @param deviceId 设备在推送的唯一标示，32位，数字和小写字母组合
     * @param alias    需要绑定的Alias，一次最多只能绑定10个，多个Alias用逗号分隔，Alias最长128个字节（中文算三个字符），一个设备最多绑定128个别名，一个别名最多允许绑定128个设备
     *                 <p>
     *                 参考文档 ：https://help.aliyun.com/document_detail/48079.html
     * @return 绑定成功或失败
     */
    public boolean bindAlias(@Nonnull String deviceId, @Nonnull String alias) {
        BindAliasRequest request = new BindAliasRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setDeviceId(deviceId);
        request.setAliasName(alias);
        try {
            client.getAcsResponse(request);
            return true;
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 查询设备绑定的别名
     *
     * @param deviceId 设备在推送的唯一标示，32位，数字和小写字母组合
     * @return List<QueryAliasesResponse.AliasInfo>
     * 参考文档 ：https://help.aliyun.com/document_detail/48078.html
     */
    public List<QueryAliasesResponse.AliasInfo> queryAliases(@Nonnull String deviceId) {
        QueryAliasesRequest request = new QueryAliasesRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setDeviceId(deviceId);
        try {
            QueryAliasesResponse response = client.getAcsResponse(request);
            return response.getAliasInfos();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();

    }


    /**
     * 解绑别名
     *
     * @param deviceId  设备在推送的唯一标示，32位，数字和小写字母组合
     * @param unbindAll 是否全部解绑，默认为”false”，如果值为”true”，则解绑一个设备当前绑定的所有别名；如果值为”false”，则解绑”AliasName”指定的别名。
     * @param aliasName 需要绑定的Alias，Alias支持128个字节（中文算3个），当”UnbindAll”为”false”时该字段必须，一次最多解绑10个，多个Alias用逗号分隔
     *                  <p>
     *                  https://help.aliyun.com/document_detail/48080.html
     * @return 解绑成功或失败
     */
    public boolean unbindAlias(@Nonnull String deviceId, @Nonnull boolean unbindAll, @Nullable String aliasName){
        UnbindAliasRequest request = new UnbindAliasRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setDeviceId(deviceId);
        request.setAliasName(aliasName);
        try {
            client.getAcsResponse(request);
            return true;
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 查询用户已创建的APP列表。
     * 注：目前APP的创建app最大个数有限制，一般为10个，不做分页处理<p>
     * 参考文档 ：https://help.aliyun.com/document_detail/48069.html
     */
    public List<ListSummaryAppsResponse.SummaryAppInfo> queryApps() {
        ListSummaryAppsRequest request = new ListSummaryAppsRequest();
        try {
            ListSummaryAppsResponse response = client.getAcsResponse(request);
            return response.getSummaryAppInfos();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 查询用户app的推送记录。
     *
     * @param startDate  查询的起始时间
     * @param endDate    查询的结束时间
     * @param pushType   查询消息的类型, MESSAGE：表示消息，NOTICE：表示通知
     * @param pageNumber 推送记录的当前页数, 默认为1
     * @param pageSize   每页的条目，默认值为20, 最大为100
     * @return List<ListPushRecordsResponse.PushMessageInfo>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48095.html?spm=a2c4g.11186631.2.1.5rUZkH</p>
     */
    public List<ListPushRecordsResponse.PushMessageInfo> queryPushRecords(@Nonnull Date startDate, @Nonnull Date endDate, @Nonnull PushType pushType,
                                                                          @Nullable Integer pageNumber, @Nullable Integer pageSize) {
        ListPushRecordsRequest request = new ListPushRecordsRequest();
        request.setAppKey(properties.getPushAppKey());
        ISO8601DateFormat format = new ISO8601DateFormat();
        request.setStartTime(format.format(startDate));
        request.setEndTime(format.format(endDate));
        request.setPushType(pushType.name());
        request.setPage(pageNumber);
        request.setPageSize(pageSize);
        try {
            ListPushRecordsResponse response = client.getAcsResponse(request);
            return response.getPushMessageInfos();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 查询APP维度的推送统计
     *
     * @param startDate   查询的起始时间
     * @param endDate     查询的结束时间
     * @param granularity 返回的数据粒度，HOUR：是小时粒度，DAY：是天粒度。小时粒度允许查24小时内数据，天粒度允许查31内天数据，目前只支持天粒度查询。
     * @return List<QueryPushStatByAppResponse.AppPushStat>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48093.html</p>
     */
    public List<QueryPushStatByAppResponse.AppPushStat> queryPushStatByApp(@Nonnull Date startDate, @Nonnull Date endDate, @Nonnull Granularity granularity) {
        QueryPushStatByAppRequest request = new QueryPushStatByAppRequest();
        request.setAppKey(properties.getPushAppKey());
        ISO8601DateFormat format = new ISO8601DateFormat();
        request.setStartTime(format.format(startDate));
        request.setEndTime(format.format(endDate));
        request.setGranularity(Granularity.DAY.name());
        try {
            return client.getAcsResponse(request).getAppPushStats();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 任务维度推送统计
     *
     * @param messageId 推送的消息ID，推送之后会返回该ID
     * @return List<QueryPushStatByMsgResponse.PushStat>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48097.html</p>
     */
    public List<QueryPushStatByMsgResponse.PushStat> queryPushStatByMsg(@Nonnull String messageId) {
        QueryPushStatByMsgRequest request = new QueryPushStatByMsgRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setMessageId(messageId);
        try {
            return client.getAcsResponse(request).getPushStats();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 查询App维度的设备统计。目前只支持返回天的数据，天维度最多支持查31天内数据。
     *
     * @param startDate  查询的起始时间
     * @param endDate    查询的结束时间
     * @param deviceType 设备类型
     * @param queryType  查询的是新增设备数还是历史累计设备数
     * @return List<QueryDeviceStatResponse.AppDeviceStat>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48094.html</p>
     */
    public List<QueryDeviceStatResponse.AppDeviceStat> queryDeviceStat(@Nonnull Date startDate, @Nonnull Date endDate, @Nonnull DeviceType deviceType, @Nonnull QueryType queryType) {
        QueryDeviceStatRequest request = new QueryDeviceStatRequest();
        request.setAppKey(properties.getPushAppKey());
        ISO8601DateFormat format = new ISO8601DateFormat();
        request.setStartTime(format.format(startDate));
        request.setEndTime(format.format(endDate));
        request.setDeviceType(deviceType.name());
        request.setQueryType(queryType.name());
        try {
            return client.getAcsResponse(request).getAppDeviceStats();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 查询App维度的去重设备统计。目前只支持返回天的数据，天维度最多支持查31天内数据。去重设备统计是以月为周期计数，每个月月初都会清零重新计数。
     *
     * @param startDate 查询的起始时间
     * @param endDate   查询的结束时间
     * @return List<QueryUniqueDeviceStatResponse.AppDeviceStat>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48092.html</p>
     */
    public List<QueryUniqueDeviceStatResponse.AppDeviceStat> queryUniqueDeviceStat(@Nonnull Date startDate, @Nonnull Date endDate) {
        QueryUniqueDeviceStatRequest request = new QueryUniqueDeviceStatRequest();
        request.setAppKey(properties.getPushAppKey());
        ISO8601DateFormat format = new ISO8601DateFormat();
        request.setStartTime(format.format(startDate));
        request.setEndTime(format.format(endDate));
        try {
            return client.getAcsResponse(request).getAppDeviceStats();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 查询设备详情
     *
     * @param deviceId 设备在推送系统中的唯一标识(一次只能查询一个)
     * @return QueryDeviceInfoResponse.DeviceInfo
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48098.html</p>
     */
    public QueryDeviceInfoResponse.DeviceInfo queryDeviceInfo(@Nonnull String deviceId) {
        QueryDeviceInfoRequest request = new QueryDeviceInfoRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setDeviceId(deviceId);
        try {
            return client.getAcsResponse(request).getDeviceInfo();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 批量验证设备有效性
     *
     * @param deviceIds 设备在推送的唯一标示，32位，数字和小写字母组合，多个设备查询用“，”分割（一次最多查100个）
     * @return List<CheckDevicesResponse.DeviceCheckInfo>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/51025.html</p>
     */
    public List<CheckDevicesResponse.DeviceCheckInfo> checkDevices(@Nonnull String deviceIds) {
        CheckDevicesRequest request = new CheckDevicesRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setDeviceIds(deviceIds);
        try {
            return client.getAcsResponse(request).getDeviceCheckInfos();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 验证一个设备有效性
     *
     * @param deviceId 设备在推送的唯一标示
     * @return 有效性
     */
    public boolean checkDeviceAvailable(@Nonnull String deviceId) {
        CheckDeviceRequest request = new CheckDeviceRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setDeviceId(deviceId);
        try {
            Boolean result = client.getAcsResponse(request).getAvailable();
            return result != null ? result : false;
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 绑定TAG。注意：TAG绑定在10分钟内生效。
     *
     * @param clientKey 设备或account以及alias，多个key用逗号分隔，最多支持1000个
     * @param keyType   ClientKey的类型，DEVICE：是设备，ACCOUNT：是账号，ALIAS：是别名
     * @param tagName   绑定的Tag，多个Tag用逗号分隔，系统总共支持1万个Tag，一次最多能绑定10个Tag
     * @return 绑定成功或失败
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48083.html</p>
     */
    public boolean bindTag(@Nonnull String clientKey, @Nonnull KeyType keyType, @Nonnull String tagName) {
        BindTagRequest request = new BindTagRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setClientKey(clientKey);
        request.setKeyType(keyType.name());
        request.setTagName(tagName);
        try {
            client.getAcsResponse(request);
            return true;
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 查询某个对象的TAG列表
     *
     * @param clientKey 设备或account以及alias，多个key用逗号分隔，最多支持1000个
     * @param keyType   ClientKey的类型，DEVICE：是设备，ACCOUNT：是账号，ALIAS：是别名
     * @return List<QueryTagsResponse.TagInfo>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48081.html</p>
     */
    public List<QueryTagsResponse.TagInfo> queryTags(@Nonnull String clientKey, @Nonnull KeyType keyType) {
        QueryTagsRequest request = new QueryTagsRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setClientKey(clientKey);
        request.setKeyType(keyType.name());
        try {
            return client.getAcsResponse(request).getTagInfos();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 解绑TAG
     *
     * @param clientKey 设备或account或alias，多个key用逗号分隔，最多支持1000个
     * @param keyType   ClientKey的类型，DEVICE：是设备，ACCOUNT：是账号，ALIAS：是别名
     * @param tagName   绑定的Tag，多个Tag用逗号分隔，系统总共支持1万个Tag，此接口一次最多能绑定10个Tag
     * @return 解绑成功或失败
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48084.html</p>
     */
    public boolean unbindTag(@Nonnull String clientKey, @Nonnull KeyType keyType, @Nonnull String tagName) {
        UnbindTagRequest request = new UnbindTagRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setClientKey(clientKey);
        request.setKeyType(keyType.name());
        request.setTagName(tagName);
        try {
            client.getAcsResponse(request);
            return true;
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 查询App全部的TAG列表
     *
     * @return List<ListTagsResponse.TagInfo>
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/48082.html</p>
     */
    public List<ListTagsResponse.TagInfo> listTags() {
        ListTagsRequest request = new ListTagsRequest();
        request.setAppKey(properties.getPushAppKey());
        try {
            client.getAcsResponse(request).getTagInfos();
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 删除TAG。注意：该操作会解除目标TAG与其下所有设备/账号/别名的绑定关系，请谨慎操作。
     *
     * @param tagName 要删除的Tag名称（单次只能删除一个Tag）
     * @return 删除成功或失败
     * <p>参考文档：https://help.aliyun.com/knowledge_detail/56005.html</p>
     */
    public boolean removeTag(@Nonnull String tagName) {
        RemoveTagRequest request = new RemoveTagRequest();
        request.setAppKey(properties.getPushAppKey());
        request.setTagName(tagName);
        try {
            client.getAcsResponse(request);
            return true;
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}
