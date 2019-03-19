package io.easyspring.service.message;

/**
 * 消息发送的方式类型
 *
 * @author summer
 * @date 2019-03-12 14:19
 * @version V1.0.0-RELEASE
 */
public enum MessageChannelType {
    /**
     * 短信消息
     */
    SMS,
    /**
     * 邮件消息
     */
    EMAIL,
    /**
     * 系统消息
     */
    SYSTEM,

    ;
}
