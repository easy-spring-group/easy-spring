package io.easyspring.service.message.subset.email;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 邮件消息的发送器
 *
 * @author summer
 * @date 2019-03-13 12:56
 * @version V1.0.0-RELEASE
 */
@Validated
public interface EmailMessageSender {

    /**
     * 邮件发送方法
     *
     * @param message 邮件消息对象
     * @return com.yinbaochina.management.risk.message.manage.Email.EmailMessage
     * @author summer
     * @date 2019-03-13 13:27
     * @version V1.0.0-RELEASE
     */
    void send(@NotNull(message = "邮件消息对象不能为空") @Valid EmailMessage message);
}
