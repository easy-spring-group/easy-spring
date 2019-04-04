package io.easyspring.message;

import io.easyspring.message.support.EasyMessage;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 消息的发送器
 *
 * @author summer
 * DateTime 2019-03-13 12:56
 * @version V1.0.0-RELEASE
 */
@Validated
public interface MessageSender<T extends EasyMessage> {

    /**
     * 消息发送方法
     *
     * @param message 消息对象
     * Author summer
     * DateTime 2019-03-13 13:27
     * Version V1.0.0-RELEASE
     */
    void send(@NotNull(message = "消息对象不能为空") @Valid T message);
}
