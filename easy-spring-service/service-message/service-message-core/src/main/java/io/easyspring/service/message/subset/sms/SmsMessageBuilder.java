package io.easyspring.service.message.subset.sms;

import io.easyspring.service.message.MessageBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * 短信消息的封装器
 *
 * @author summer
 * @date 2019-03-13 12:56
 * @version V1.0.0-RELEASE
 */
@Validated
public interface SmsMessageBuilder extends MessageBuilder<SmsMessage, SmsReceiver> {
}
