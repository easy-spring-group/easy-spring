package io.easyspring.service.message.subset.system;

import io.easyspring.service.message.EasyMessageException;
import io.easyspring.service.message.MessageBuilder;
import io.easyspring.service.message.MessageChannelType;
import io.easyspring.service.message.MessageSender;
import io.easyspring.service.message.impl.AbstractMessageProcessor;
import io.easyspring.service.message.support.EasyMessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统消息的管理器
 *
 * @author summer
 * @date 2019-03-15 14:59
 * @version V1.0.0-RELEASE
 */
@Component("systemMessageProcessor")
@Slf4j
public class SystemMessageProcessor extends AbstractMessageProcessor<SystemMessage> {

    /**
     * 系统消息的封装器
     */
    @Autowired
    private MessageBuilder<SystemMessage, SystemReceiver> systemMessageBuilder;
    /**
     * 系统消息的发送器
     */
    @Autowired
    private MessageSender<SystemMessage> systemMessageSender;

    /**
     * 封装用于发送的消息
     *
     * @param receiverList 接收者集合
     * @param easyMessageTemplate 消息模板对象
     * @param extend 需要替换的参数
     * @return M
     * @author summer
     * @date 2019-03-15 18:01
     * @version V1.0.0-RELEASE
     */
    @Override
    protected SystemMessage builder(List<String> receiverList,
                                   EasyMessageTemplate easyMessageTemplate,
                                   Map<String, Object> extend){
        /*
         * 参数校验
         */
        // 获取消息模板的通道类型
        MessageChannelType channelType = easyMessageTemplate.getChannelType();
        // 如果通道类型为空, 或者不是 SYSTEM 通道, 则直接抛出异常
        if (channelType == null || !channelType.equals(MessageChannelType.SYSTEM)) {
            throw new EasyMessageException("模板通道类型不匹配, 当前模板类型: " + channelType);
        }

        // 执行封装并返回封装后的消息对象
        return systemMessageBuilder.builder(buildSystemReceiver(receiverList), easyMessageTemplate, extend);
    }

    /**
     * 发送系统消息的方法
     *
     * @param message 系统消息信息
     * @return void
     * @author summer
     * @date 2019-03-15 13:45
     * @version V1.0.0-RELEASE
     */
    @Override
    protected void send(SystemMessage message) {
        // 消息通道校验
        if (!MessageChannelType.SYSTEM.equals(message.getChannelType())) {
            throw new EasyMessageException("消息通道类型不匹配, 当前类型: " + message.getChannelType());
        }

        // 调用发送器的发送方法
        systemMessageSender.send(message);
    }

    /**
     * 封装系统消息接收者的方法
     *
     * @param systemStringReceiverList String 类型的系统消息接收者集合
     * @return java.util.List<io.easyspring.service.message.subset.system.SystemReceiver>
     * @author summer
     * @date 2019-03-15 15:45
     * @version V1.0.0-RELEASE
     */
    private List<SystemReceiver> buildSystemReceiver(List<String> systemStringReceiverList) {
        // 参数校验
        if (CollectionUtils.isEmpty(systemStringReceiverList)) {
            return null;
        }

        // 循环封装接收者并返回数据
        return systemStringReceiverList.stream()
                // 循环执行转换
                .map(stringReceiver -> {

                    try {
                        // 把 String 类型的接收者转换成 Long 类型
                        Long receiver = Long.valueOf(stringReceiver);
                        // 创建新的接收者对象并返回
                        return new SystemReceiver(receiver);
                    } catch (NumberFormatException e) {
                        // 如果传入的字符串不能转换成 Long 则抛出异常
                        log.error("数据转换错误, 您输入的字符串不能转换成 Long 类型的系统消息接收者 id, 输入值是: {}",
                                stringReceiver);
                        throw new EasyMessageException("请输入正确的系统消息接收者 id");
                    }
                }).collect(Collectors.toList());
    }
}
