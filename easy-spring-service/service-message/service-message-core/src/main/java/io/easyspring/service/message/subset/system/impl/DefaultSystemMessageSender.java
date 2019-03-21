package io.easyspring.service.message.subset.system.impl;

import io.easyspring.service.message.MessageSender;
import io.easyspring.service.message.subset.system.SystemMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统消息发送器的默认实现
 *
 * @author summer
 * DateTime 2019-03-13 13:28
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultSystemMessageSender implements MessageSender<SystemMessage> {

    /**
     * 系统消息发送方法
     *
     * @param message 系统消息对象
     * Author summer
     * DateTime 2019-03-13 13:27
     * Version V1.0.0-RELEASE
     */
    @Override
    public void send(SystemMessage message) {
        /*
         * 输出需要自己实现的提示
         */
        log.warn("[系统消息发送] 默认的系统消息发送器, 如需自定义请实现 SystemMessageSender 接口");
        log.info("[系统消息发送] 将要发送的系统消息是: {}", message);
    }
}
