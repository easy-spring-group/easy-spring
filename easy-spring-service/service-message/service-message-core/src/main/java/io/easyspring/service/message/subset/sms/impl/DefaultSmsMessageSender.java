package io.easyspring.service.message.subset.sms.impl;

import io.easyspring.service.message.MessageSender;
import io.easyspring.service.message.subset.sms.SmsMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信发送器的默认实现
 *
 * @author summer
 * DateTime 2019-03-13 13:28
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultSmsMessageSender implements MessageSender<SmsMessage> {

    /**
     * 短信发送方法
     *
     * @param message 短信消息对象
     * Author summer
     * DateTime 2019-03-13 13:27
     * Version V1.0.0-RELEASE
     */
    @Override
    public void send(SmsMessage message) {
        /*
         * 输出需要自己实现的提示
         */
        log.warn("[消息发送] 默认的短信发送器, 使用时需要自己实现 SmsMessageSender");
        log.info("[消息发送] 将要发送的短信消息是: {}", message);
    }
}
