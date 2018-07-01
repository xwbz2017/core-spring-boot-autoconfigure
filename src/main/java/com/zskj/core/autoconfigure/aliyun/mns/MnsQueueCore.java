package com.zskj.core.autoconfigure.aliyun.mns;

import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.PagingListResult;
import com.aliyun.mns.model.QueueMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 阿里云消息服务-消息队列相关
 *
 * @author 花开
 * @date 2018/2/2
 */
public class MnsQueueCore {

    private Logger logger = LoggerFactory.getLogger(MnsQueueCore.class);

    private MNSClient client;

    public MnsQueueCore(MNSClient client) {
        this.client = client;
    }

    /**
     * 创建队列
     *
     * @param queueMeta 队列属性
     * @return 创建的 QueueURL，格式如下：http://$AccountId.mns.<Region>.aliyuncs.com/queues/$queueName
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35129.html
     * </p>
     */
    public @CheckForNull
    String createQueue(@Nonnull QueueMeta queueMeta) {
        try {
            return client.createQueue(queueMeta).getQueueURL();
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 删除队列
     *
     * @param queueName 队列名称
     * @return 删除成功或失败
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35132.html
     * </p>
     */
    public boolean deleteQueue(@Nonnull String queueName) {
        try {
            client.getQueueRef(queueName).delete();
            return true;
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 获取队列属性
     *
     * @param queueName 队列名称
     * @return QueueMeta
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35131.html
     * </p>
     */
    public @CheckForNull
    QueueMeta getQueueMeta(@Nonnull String queueName) {
        try {
            return client.getQueueRef(queueName).getAttributes();
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 更新队列属性
     *
     * @param queueMeta 队列属性
     * @return 更新成功或失败
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35130.html
     * </p>
     */
    public boolean updateQueueMeta(QueueMeta queueMeta) {
        try {
            client.getQueueRef(queueMeta.getQueueName()).setAttributes(queueMeta);
            return true;
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 获取队列列表
     * 返回结果中只包含 QueueURL 属性
     *
     * @param prefix   按照该前缀开头的 queueName 进行查找。
     * @param marker   下一个分页的开始位置，一般从上次分页结果返回的NextMarker获取。
     * @param pageSize 单次请求结果的最大返回个数，可以取1-1000范围内的整数值，默认值为1000。
     * @return PagingListResult<QueueMeta>
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35133.html
     * </p>
     */
    public @CheckForNull
    PagingListResult<QueueMeta> listQueue(@Nullable String prefix, @Nullable String marker, @Nullable Integer pageSize) {
        try {
            return client.listQueue(prefix, marker, pageSize);
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 发送消息
     *
     * @param queueName   队列名称
     * @param messageBody 消息体
     * @return MessageId MessageBodyMD5 ReceiptHandle
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35134.html
     * </p>
     */
    public Message sendMessage(@Nonnull String queueName, @Nonnull String messageBody) {
        return sendMessage(queueName, messageBody, null, null);
    }

    /**
     * 发送消息
     *
     * @param queueName    队列名称
     * @param messageBody  消息体
     * @param delaySeconds 指定的秒数延后可被消费，单位为秒 0-604800秒（7天）范围内某个整数值，默认值为0
     * @param priority     指定消息的优先级权值，优先级越高的消息，越容易更早被消费 取值范围1~16（其中1为最高优先级），默认优先级为8
     * @return MessageId MessageBodyMD5 ReceiptHandle
     */
    public @CheckForNull
    Message sendMessage(@Nonnull String queueName, @Nonnull String messageBody,
                        @Nullable Integer delaySeconds, @Nullable Integer priority) {
        try {
            Message message = new Message();
            message.setMessageBody(messageBody);
            if (delaySeconds != null) {
                message.setDelaySeconds(delaySeconds);
            }
            if (priority != null) {
                message.setPriority(priority);
            }
            return client.getQueueRef(queueName).putMessage(message);
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 批量发送消息
     *
     * @param queueName       队列名称
     * @param messageBodyList 消息体
     * @return MessageId MessageBodyMD5 ReceiptHandle 注意可能部分发成功，部分失败，失败返回ErrorCode ErrorMessage
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35135.html
     * </p>
     */
    public List<Message> batchSendMessage(@Nonnull String queueName, @Nonnull List<String> messageBodyList) {
        return batchSendMessage(queueName, messageBodyList, null, null);
    }

    /**
     * 批量发送消息
     *
     * @param queueName       队列名称
     * @param messageBodyList 消息体
     * @param delaySeconds    指定的秒数延后可被消费，单位为秒 0-604800秒（7天）范围内某个整数值，默认值为0
     * @param priority        指定消息的优先级权值，优先级越高的消息，越容易更早被消费 取值范围1~16（其中1为最高优先级），默认优先级为8
     * @return MessageId MessageBodyMD5 ReceiptHandle 注意可能部分发成功，部分失败，失败返回ErrorCode ErrorMessage
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35135.html
     * </p>
     */
    public List<Message> batchSendMessage(@Nonnull String queueName, @Nonnull List<String> messageBodyList,
                                          @Nullable Integer delaySeconds, @Nullable Integer priority) {
        try {
            List<Message> messages = new ArrayList<>();
            for (String messageBody : messageBodyList) {
                Message message = new Message();
                message.setMessageBody(messageBody);
                if (delaySeconds != null) {
                    message.setDelaySeconds(delaySeconds);
                }
                if (priority != null) {
                    message.setPriority(priority);
                }
                messages.add(message);
            }
            return client.getQueueRef(queueName).batchPutMessage(messages);
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    // TODO 消费消息、批量消费消息

    /**
     * 删除消息
     *
     * @param queueName     队列名
     * @param receiptHandle 上次消费后返回的消息ReceiptHandle，详见本文ReceiveMessage接口
     * @return 删除成功或失败
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35138.html
     * </p>
     */
    public boolean deleteMessage(@Nonnull String queueName, @Nonnull String receiptHandle) {
        try {
            client.getQueueRef(queueName).deleteMessage(receiptHandle);
            return true;
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 批量删除消息
     *
     * @param queueName      队列名
     * @param receiptHandles ReceiptHandle集合
     * @return 删除成功或失败，此处返回值未判断，正确来说有部分删除成功失败的具体返回值
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35139.html
     * </p>
     */
    public boolean batchDeleteMessage(@Nonnull String queueName, @Nonnull List<String> receiptHandles) {
        try {
            client.getQueueRef(queueName).batchDeleteMessage(receiptHandles);
            return true;
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 查看队列顶部的消息
     *
     * @param queueName 队列名称
     * @return 消息详情
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35140.html
     * </p>
     */
    public Message peekMessage(@Nonnull String queueName) {
        try {
            return client.getQueueRef(queueName).peekMessage();
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 批量查看消息
     *
     * @param queueName 队列名称
     * @param batchSize 本次最多查看消息的条数
     * @return 消息列表
     * <p>
     * 参考文档：https://help.aliyun.com/document_detail/35141.html
     * </p>
     */
    public List<Message> batchPeekMessage(@Nonnull String queueName, int batchSize) {
        try {
            return client.getQueueRef(queueName).batchPeekMessage(batchSize);
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * 修改被消费过并且还处于的 Inactive 的消息到下次可被消费的时间，成功修改消息的 VisibilityTimeout 后，返回新的 ReceiptHandle。
     *
     * @param queueName         队列名
     * @param receiptHandle     消息句柄
     * @param visibilityTimeout 从现在到下次可被用来消费的时间间隔，单位为秒
     * @return ReceiptHandle NextVisibleTime
     */
    public Message changeMessageVisibility(@Nonnull String queueName, @Nonnull String receiptHandle, int visibilityTimeout) {
        try {
            return client.getQueueRef(queueName).changeMessageVisibility(receiptHandle, visibilityTimeout);
        } catch (ClientException ce) {
            logger.error(ce.toString(), ce);
        } catch (ServiceException se) {
            logger.error(se.toString(), se);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
