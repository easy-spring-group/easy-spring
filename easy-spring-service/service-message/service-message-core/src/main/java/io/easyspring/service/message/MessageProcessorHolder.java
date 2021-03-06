package io.easyspring.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 消息处理器的管理器
 *
 * @author summer
 * DateTime 2019-03-13 13:53
 * @version V1.0.0-RELEASE
 */
public class MessageProcessorHolder {

    /**
     * 注入消息处理器的集合
     */
    @Autowired
    private Map<String, MessageProcessor> messageProcessorMap;

    /**
     * 根据消息的通道类型, 获取对应的消息处理器
     *
     * @param messageChannelType 消息通道类型
     * @return io.easyspring.service.message.MessageProcessor
     * Author summer
     * DateTime 2019-03-13 14:00
     * Version V1.0.0-RELEASE
     */
    public MessageProcessor findMessageProcessor(MessageChannelType messageChannelType){
        // 参数校验
        if (messageChannelType == null) {
            throw new EasyMessageException("消息通道类型不能为空");
        }

        // 根据消息通道类型的字符串, 获取对应的消息处理器
        return findMessageProcessor(messageChannelType.toString().toLowerCase());
    }

    /**
     * 根据消息通道类型的字符串, 获取对应的消息处理器
     *
     * @param messageChannelType 消息通道类型的字符串
     * @return io.easyspring.service.message.MessageProcessor
     * Author summer
     * DateTime 2019-03-13 14:01
     * Version V1.0.0-RELEASE
     */
    public MessageProcessor findMessageProcessor(String messageChannelType) {
        // 参数校验
        if (StringUtils.isEmpty(messageChannelType)) {
            throw new EasyMessageException("消息通道类型不能为空");
        }

        // 获取消息处理器的名称
        String messageProcessorName = messageChannelType.toLowerCase() + MessageProcessor.class.getSimpleName();

        // 获取对应的消息处理器
        MessageProcessor messageProcessor = messageProcessorMap.get(messageProcessorName);

        // 如果最终获取到的消息处理器为空
        if (messageProcessor == null) {
            throw new EasyMessageException("消息处理器" + messageProcessorName + "不存在");
        }

        return messageProcessor;
    }

}
