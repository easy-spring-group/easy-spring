package io.easyspring.service.message.subset.email;

import io.easyspring.service.message.MessageBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * 邮件消息的封装器
 *
 * @author summer
 * @date 2019-03-15 14:44
 * @version V1.0.0-RELEASE
 */
@Validated
public interface EmailMessageBuilder extends MessageBuilder<EmailMessage, EmailReceiver> {
}