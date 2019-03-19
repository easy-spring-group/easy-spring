package io.easyspring.service.message.subset.system;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 系统消息的发送器
 *
 * @author summer
 * @date 2019-03-13 12:56
 * @version V1.0.0-RELEASE
 */
@Validated
public interface SystemMessageSender {

    /**
     * 系统消息发送方法
     *
     * @param message 系统消息对象
     * @return com.yinbaochina.management.risk.message.manage.System.SystemMessage
     * @author summer
     * @date 2019-03-13 13:27
     * @version V1.0.0-RELEASE
     */
    void send(@NotNull(message = "系统消息对象不能为空") @Valid SystemMessage message);
}
