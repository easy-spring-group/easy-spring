package io.easyspring.message.core.subset.sms.impl;

import io.easyspring.message.core.EasyMessageException;
import io.easyspring.message.core.MessageBuilder;
import io.easyspring.message.core.subset.sms.SmsMessage;
import io.easyspring.message.core.subset.sms.SmsReceiver;
import io.easyspring.message.core.support.EasyMessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import java.util.List;
import java.util.Map;

/**
 * 默认的短信消息的格式化类
 *
 * @author summer
 * DateTime 2019-03-13 13:12
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultSmsMessageBuilder implements MessageBuilder<SmsMessage, SmsReceiver> {

    /**
     * 默认的短信发送者
     */
    private static final String DEFAULT_SMS_SENDER = "短信通道提供商";

    /**
     * 格式化短信消息的方法
     *
     * @param receiverList 消息接收者集合
     * @param easyMessageTemplate 短信息消息模板
     * @param extend 需要替换的值
     * @return io.easyspring.service.message.subset.sms.SmsMessage
     * Author summer
     * DateTime 2019-03-13 13:13
     * Version V1.0.0-RELEASE
     */
    @Override
    public SmsMessage builder(List<SmsReceiver> receiverList,
                              EasyMessageTemplate easyMessageTemplate,
                              Map<String, Object> extend) {

        log.info("[消息格式化] 短信消息格式化, 需要格式化的模板是: {}", easyMessageTemplate);
        /*
         * 格式化消息
         */
        // 获取模板内容
        String template = easyMessageTemplate.getTemplate();
        // 对模板体进行校验
        if (StringUtils.isBlank(template)) {
            throw new EasyMessageException("短信模板内容不能为空");
        }
        // 封装模板到 content 中
        String content = processContent(template, extend);

        /*
         * 封装短信消息
         */
        SmsMessage smsMessage = new SmsMessage();
        // 设置发送者
        smsMessage.setSender(null);
        // 设置接收者
        smsMessage.setReceiverList(receiverList);
        // 设置发送类型
        smsMessage.setChannelType(easyMessageTemplate.getChannelType());
        // 设置消息类型
        smsMessage.setMessageType(easyMessageTemplate.getMessageType());
        // 设置消息内容
        smsMessage.setContent(content);
        // 设置消息的发送者
        smsMessage.setSender(DEFAULT_SMS_SENDER);
        // 设置消息模板的识别码
        smsMessage.setTemplateCode(easyMessageTemplate.getTemplateCode());

        // 设置数据信息
        smsMessage.setExtend(extend);

        // 返回封装好的内容
        return smsMessage;
    }

    /**
     * 格式化内容, 替换 template 中的占位符为真实的数据
     *
     * @param template 内容模板
     * @param replaceValue 需要替换的数据
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-12 15:37
     * Version V1.0.0-RELEASE
     */
    private String processContent(String template, Map<String, Object> replaceValue) {

        // 定义取代器
        StrSubstitutor strSubstitutor = new StrSubstitutor(replaceValue);
        // 执行替换并返回
        return strSubstitutor.replace(template);
    }
}
