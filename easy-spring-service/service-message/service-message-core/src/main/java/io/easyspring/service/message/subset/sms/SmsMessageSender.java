package io.easyspring.service.message.subset.sms;

import io.easyspring.service.message.MessageSender;
import org.springframework.validation.annotation.Validated;

/**
 * 短信消息的发送器
 *
 * @author summer
 * @date 2019-03-13 12:56
 * @version V1.0.0-RELEASE
 */
@Validated
public interface SmsMessageSender extends MessageSender<SmsMessage> {
}
