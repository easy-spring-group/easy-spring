package io.easyspring.service.message.subset.email;

import io.easyspring.service.message.EasyMessageException;
import io.easyspring.service.message.MessageChannelType;
import io.easyspring.service.message.impl.AbstractMessageProcessor;
import io.easyspring.service.message.support.EasyMessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 邮件消息的管理器
 *
 * @author summer
 * @date 2019-03-15 14:59
 * @version V1.0.0-RELEASE
 */
@Component("emailMessageProcessor")
public class EmailMessageProcessor extends AbstractMessageProcessor<EmailMessage> {

    /**
     * 邮件消息的封装器
     */
    @Autowired
    private EmailMessageBuilder emailMessageBuilder;
    /**
     * 邮件消息的发送器
     */
    @Autowired
    private EmailMessageSender emailMessageSender;

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
    protected EmailMessage builder(List<String> receiverList,
                                   EasyMessageTemplate easyMessageTemplate,
                                   Map<String, Object> extend){
        /*
         * 参数校验
         */
        // 获取消息模板的通道类型
        MessageChannelType channelType = easyMessageTemplate.getChannelType();
        // 如果通道类型为空, 或者不是 EMAIL 通道, 则直接抛出异常
        if (channelType == null || !channelType.equals(MessageChannelType.EMAIL)) {
            throw new EasyMessageException("模板通道类型不匹配, 当前模板类型: " + channelType);
        }

        // 执行封装并返回封装后的消息对象
        return emailMessageBuilder.builder(buildEmailReceiver(receiverList), easyMessageTemplate, extend);
    }

    /**
     * 发送邮件的方法
     *
     * @param message 邮件信息
     * @return void
     * @author summer
     * @date 2019-03-15 13:45
     * @version V1.0.0-RELEASE
     */
    @Override
    protected void send(EmailMessage message) {
        // 消息通道校验
        if (!MessageChannelType.EMAIL.equals(message.getChannelType())) {
            throw new EasyMessageException("消息通道类型不匹配, 当前类型: " + message.getChannelType());
        }

        // 调用发送器的发送方法
        emailMessageSender.send(message);
    }

    /**
     * 封装邮件接收者的方法
     *
     * @param emailStringReceiverList String 类型的邮件接收者集合
     * @return java.util.List<io.easyspring.service.message.subset.email.EmailReceiver>
     * @author summer
     * @date 2019-03-15 15:45
     * @version V1.0.0-RELEASE
     */
    private List<EmailReceiver> buildEmailReceiver(List<String> emailStringReceiverList) {
        // 参数校验
        if (CollectionUtils.isEmpty(emailStringReceiverList)) {
            return null;
        }

        // 循环封装接收者并返回数据
        return emailStringReceiverList.stream()
                .map(EmailReceiver::new)
                .collect(Collectors.toList());
    }
}
