package io.easyspring.service.message.subset.sms;

import io.easyspring.service.message.EasyMessageException;
import io.easyspring.service.message.MessageBuilder;
import io.easyspring.service.message.MessageChannelType;
import io.easyspring.service.message.MessageSender;
import io.easyspring.service.message.impl.AbstractMessageProcessor;
import io.easyspring.service.message.support.EasyMessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 短信消息的管理器
 *
 * @author summer
 * @date 2019-03-12 14:59
 * @version V1.0.0-RELEASE
 */
public class SmsMessageProcessor extends AbstractMessageProcessor<SmsMessage> {

    /**
     * 短信消息的封装器
     */
    @Autowired
    private MessageBuilder<SmsMessage, SmsReceiver> smsMessageBuilder;
    /**
     * 短信消息的发送器
     */
    @Autowired
    private MessageSender<SmsMessage> smsMessageSender;

    /**
     * 封装用于发送的消息
     *
     * @param receiverList 接收者集合
     * @param easyMessageTemplate 消息模板对象
     * @param extend 需要替换的参数
     * @return M
     * @author summer
     * @date 2019-03-12 18:01
     * @version V1.0.0-RELEASE
     */
    @Override
    protected SmsMessage builder(List<String> receiverList,
                                 EasyMessageTemplate easyMessageTemplate,
                                 Map<String, Object> extend){
        /*
         * 参数校验
         */
        // 获取消息模板的通道类型
        MessageChannelType channelType = easyMessageTemplate.getChannelType();
        // 如果通道类型为空, 或者不是 SMS 通道, 则直接抛出异常
        if (channelType == null || !channelType.equals(MessageChannelType.SMS)) {
            throw new EasyMessageException("模板通道类型不匹配, 当前模板类型: " + channelType);
        }


        // 执行封装并返回封装后的消息对象
        return smsMessageBuilder.builder(buildSmsReceiver(receiverList),
                easyMessageTemplate, extend);
    }

    /**
     * 发送短信的方法
     *
     * @param message 短信信息
     * @return void
     * @author summer
     * @date 2019-03-13 13:45
     * @version V1.0.0-RELEASE
     */
    @Override
    protected void send(SmsMessage message) {
        // 消息通道校验
        if (!MessageChannelType.SMS.equals(message.getChannelType())) {
            throw new EasyMessageException("消息通道类型不匹配, 当前类型: " + message.getChannelType());
        }

        // 调用发送器的发送方法
        smsMessageSender.send(message);
    }

    /**
     * 封装短信接收者的方法
     *
     * @param smsStringReceiverList String 类型的短信接收者集合
     * @return java.util.List<io.easyspring.service.message.subset.sms.SmsReceiver>
     * @author summer
     * @date 2019-03-15 15:45
     * @version V1.0.0-RELEASE
     */
    private List<SmsReceiver> buildSmsReceiver(List<String> smsStringReceiverList) {
        // 参数校验
        if (CollectionUtils.isEmpty(smsStringReceiverList)) {
            return null;
        }

        return smsStringReceiverList.stream()
                .map(SmsReceiver::new)
                .collect(Collectors.toList());
    }
}
