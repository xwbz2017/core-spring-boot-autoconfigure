# core-spring-boot-autoconfigure
spring boot自动化配置，封装第三方如百度鹰眼、阿里云api等

### 百度鹰眼
    核心库
    YinYanCore
    描述
    封装了百度鹰眼的全部api
    与百度鹰眼api文档提供的接口一一对应
    更新时间
    2018.02.26
    当前版本
    2.0.0.RELEASE
    使用方法
    1、配置如下
    core.baidu.map-enabled=true
    core.baidu.ak=百度ak
    core.baidu.service-ids=160486  # 这里可以填多个，用英文逗号分割
    2、使用方法
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
    其他
    相关api可以在com.zskj.core.autoconfigure.baidu包下找到

### 阿里云消息队列
    核心库
    MnsQueueCore
    描述
    封装了阿里云消息队列的api
    更新时间
    2018.02.02
    当前版本
    1.9.0.RELEASE
    使用方法
    1、导入相关sdk
    <dependency>
        <groupId>com.aliyun.mns</groupId>
        <artifactId>aliyun-sdk-mns</artifactId>
        <version>1.1.8</version>
        <classifier>jar-with-dependencies</classifier>
    </dependency>
    2、配置如下
    core.aliyun.access-key-id=accessId
    core.aliyun.access-key-secret=accessSecret
    core.aliyun.mns-enabled=true
    core.aliyun.mns-account-end-point=http://0000.mns.cn-beijing.aliyuncs.com/
    3、使用方法
    @Test
    public void sendMessage() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("type", 0);
        map.put("id", 200);
        Message message = queueCore.sendMessage(queueName, JSON.toJSONString(map));
     
        // {"messageBodyMD5":"6294CA12708415BE02D4636F9377D5FA","messageId":"D43715902B261DF7-1-16155C31EDC-300000012","requestId":"5A7429C22372C2C951E24666"}
        logger.info(JSON.toJSONString(message));
    }
     
    @Test
    public void createQueue() throws Exception{
        QueueMeta meta = new QueueMeta();
        meta.setQueueName("test-core");
        String url = queueCore.createQueue(meta);
     
        // http://1549135646902295.mns.cn-beijing.aliyuncs.com/queues/test-core
        logger.info(url);
    }
### 阿里云推送
    核心库
    PushCore
    描述
    封装了阿里云推送的全部api
    更新时间
    2018.02.02
    当前版本
    1.8.0.RELEASE
    使用方法
    1、导入相关sdk
    <!-- 阿里云sdk-core -->
    <dependency>
        <groupId>com.aliyun</groupId>
        <artifactId>aliyun-java-sdk-core</artifactId>
        <version>3.3.1</version>
    </dependency>
    <!-- 阿里云推送 -->
    <dependency>
        <groupId>com.aliyun</groupId>
        <artifactId>aliyun-java-sdk-push</artifactId>
        <version>3.8.0</version>
    </dependency>
    2、配置如下
    core.aliyun.push-enabled=true
    core.aliyun.access-key-id=access-key-id
    core.aliyun.access-key-secret=access-key-secret
    core.aliyun.push-app-key=app-key
    3、使用方法
    /**
     * 推送测试
     *
     * @author 花开
     * @date 2018/2/2
     */
    @SpringBootTest
    @RunWith(SpringJUnit4ClassRunner.class)
    public class PushCoreTest {
     
        private Logger logger = LoggerFactory.getLogger(PushCoreTest.class);
     
        @Autowired
        private PushCore pushCore;
        private String deviceId = "6d1dbe5476354477afa62d326cfdf88d";
        private String androidDeviceId = "e45cf31e6fb3419ebb734f6f0744789e";
        private String title = "这是一个标题哦";
        private String body = "这里存放内容哦";
     
        @Test
        public void pushMessageToIOS() throws Exception{
            String messageId = pushCore.pushMessageToIOS(Target.DEVICE, deviceId, title, body);
     
            logger.info("messageId:{}", messageId);
        }
     
        @Test
        public void pushNoticeToIOS() throws Exception{
            String messageId = pushCore.pushNoticeToIOS(Target.DEVICE, deviceId, title, body, ApnsEnv.PRODUCT, null);
     
            logger.info("messageId:{}", messageId);
        }
     
        @Test
        public void pushNoticeToAndroid() throws Exception {
            String messageId = pushCore.pushNoticeToAndroid(Target.DEVICE, androidDeviceId, title, body, null);
            logger.info("messageId:{}", messageId);
        }
     
        @Test
        public void queryDeviceInfo() throws Exception{
     
            QueryDeviceInfoResponse.DeviceInfo deviceInfo = pushCore.queryDeviceInfo(deviceId);
            logger.info(JSON.toJSONString(deviceInfo));
     
            QueryDeviceInfoResponse.DeviceInfo androidDeviceInfo = pushCore.queryDeviceInfo(androidDeviceId);
            logger.info(JSON.toJSONString(androidDeviceInfo));
        }
    }
    其他
    相关api可以在com.zskj.core.autoconfigure.aliyun.push包下找到
    目前提供了全部的推送api
    app相关
    推送相关
    查询相关
    TAG相关
    Alias相关
### 阿里云短信功能
    核心库
    SmsCore
    描述
    封装了阿里云发送短信及查询的功能
    更新时间
    2018.02.01
    当前版本
    1.7.0.RELEASE
    使用方法
    1、导入相关sdk
    <dependency>
        <groupId>com.aliyun</groupId>
        <artifactId>aliyun-java-sdk-core</artifactId>
        <version>3.3.1</version>
    </dependency>
    <dependency>
        <groupId>com.aliyun</groupId>
        <artifactId>aliyun-java-sdk-dysmsapi</artifactId>
        <version>1.0.1</version>
    </dependency>
    2、配置如下
    core.aliyun.sms-enabled=true
    core.aliyun.access-key-id=ZHEMEnLHOLMQDMPu
    core.aliyun.access-key-secret=fwgUrrUdOYgyeHvpaOx54XrgA2KXUY
    3、使用方法
    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringBootTest
    public class SmsCoreTest {
     
        @Autowired
        private SmsCore smsCore;
        String sign = "阿喔母婴";
        String templateCode = "SMS_100780115";
         
        @Test
        public void send() throws Exception{
            Map<String, Object> params = new HashMap<>();
            params.put("user", "linjieweiwu");
            params.put("pwd", "linjie666");
            List<String> phones = new ArrayList<>();
            phones.add("15026828154");
            String result = smsCore.send(sign, templateCode, params, phones, null);
            System.out.println(result);
        }
     
        @Test
        public void batchSend() throws Exception{
            List<String> phones = new ArrayList<>();
            phones.add("15026828154");
            List<String> signs = new ArrayList<>();
            signs.add(sign);
            List<Map<String, Object>> params = new ArrayList<>();
            Map<String, Object> param = new HashMap<>();
            param.put("user", "linjieweiwu");
            param.put("pwd", "linjie666");
            params.add(param);
            String result = smsCore.batchSend(phones, signs, templateCode, params);
            System.out.println(result);
        }
     
        @Test
        public void query() throws Exception{
            String phone = "15026828154";
            String sendDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
            int currentPage  = 1;
            int pageSize = 10;
            List<QuerySendDetailsResponse.SmsSendDetailDTO> smsSendDetailDTOS = smsCore.query(phone, null, sendDate, currentPage, pageSize);
     
            System.out.println(JSON.toJSONString(smsSendDetailDTOS));
        }
    }
### 支付宝支付功能
    核心库
    AliPayCore
    描述
    封装了支付宝常用的api接口
    使用方法
    1、导入alipay-sdk-java
    目前最新如下
    <dependency>
        <groupId>com.alipay</groupId>
        <artifactId>alipay-sdk-java</artifactId>
        <version>20180104135026</version>
    </dependency>
    2、配置如下
    core.ali.pay.enabled=true
    core.ali.pay.ali-pay-public-key=这里是支付提供给你的公钥
    core.ali.pay.app-id=应用 id
    core.ali.pay.app-private-key=你的私钥
    core.ali.pay.domain=回调域名
    core.ali.pay.notify-url=回调地址
    core.ali.pay.return-url=页面同步回调地址
    3、使用方法
    @Autowired
    private AliPayCore aliPayCore;
    @Test
    public void testAppPay() throws Exception {
        String outTradeNo = System.currentTimeMillis() + "";
        String totalAmount = "0.05";
        String subject = "大乐透";
        String body = "";
        String passBackParams = null;
        AlipayTradeAppPayResponse response = aliPayCore.appPay(outTradeNo, totalAmount, subject, body, passBackParams);
        System.out.println(JSON.toJSONString(response));
    }
    其他
    AliPayCore在com.zskj.core.autoconfigure.alipay目录下，具体可以看其中提供的api
    目前提供api如下
    App支付
    统一收单交易退款查询
    统一收单交易结算接口
    统一收单交易关闭接口
    统一收单交易撤销接口
    统一收单交易退款接口
    统一收单线下交易预创建
    统一收单交易创建接口
    统一收单交易支付接口
    统一收单线下交易查询
### Http请求封装
    核心库
    HttpClientCore
    描述
    提供了http常用请求的封装，基于httpclient4.5+
    
    1、配置如下
    core.http-client.enabled=true
    core.http-client.connect-request-timeout=5000 # 从连接池获取连接的超时时间
    core.http-client.connect-timeout=5000 # 连接建立时间
    core.http-client.default-max-per-route=10 # 设置每个路由上的默认连接个数
    core.http-client.max-total=20 # 连接池的最大连接数
    core.http-client.socket-timeout=5000 # 数据传输过程中数据包之间间隔的超时时间
    2、使用方法
    @Autowired
    private HttpClientCore httpClientCore;
    String strXml = httpClientCore.doPostJson(CLOSE_ORDER_URL, null, XmlUtils.toXml(closeOrderParam));
    其他
    HttpClientCore在com.zskj.core.autoconfigure.http目录下，可以查看其中api使用
    目前提供的api如下
    get请求
    post请求，form提交方式
    post请求，json文本提交
### 微信支付功能
    核心库
    WechatPayCore
    描述
    提供微信支付的api，基于HttpClientCore
    使用方法
    1、配置如下
    core.wechat.pay.enabled=true
    core.wechat.pay.app-id=appid
    core.wechat.pay.api-key=key
    core.wechat.pay.mch-id=mch
    core.wechat.pay.domain=http://api.jiedu.mobi
    core.wechat.pay.notify-url=/pay_notify/wechat
    core.wechat.pay.cert-path=apiclient_cert.p12
    2、使用方法
    @Autowired
    private WechatPayCore wechatPayCore;
    @Test
    public void prepay() throws Exception {
        String outNo = System.currentTimeMillis() + "";
        String productId = "300";
        Integer totalFee = 2;
        String body = "测试商品";
        String spbillCreateIp = "127.0.0.1";
        UnifiedOrderResult unifiedOrderResult = wechatPayCore.nativeUnifiedOrder(outNo, productId, totalFee, body, spbillCreateIp);
        System.out.println(JSON.toJSONString(unifiedOrderResult));
    }
    其他
    WechatPayCore在com.zskj.core.autoconfigure.wechat.pay目录下
    目前提供的api有
    统一下单
    查询订单
    关闭订单
    申请退款
    查询退款

## 注意
    依赖中有个core-utils的jar未提供出来，有需要可联系作者，不过该jar仅是简单的工具类，用得不多，可以根据上下文理解自己写。