package io.easyspring.service.message.subset.sms;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 短信消息的发送器
 *
 * @author summer
 * @date 2019-03-13 12:56
 * @version V1.0.0-RELEASE
 */
@Validated
public interface SmsMessageSender {

    /**
     * 短信发送方法
     *
     * @param message 短信消息对象
     * @return com.yinbaochina.management.risk.message.manage.subset.sms.SmsMessage
     * @author summer
     * @date 2019-03-13 13:27
     * @version V1.0.0-RELEASE
     */
    void send(@NotNull(message = "短信消息对象不能为空") @Valid SmsMessage message);
}
