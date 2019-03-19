package io.easyspring.service.message.properties;

/**
 * 消息的 Redis key 前缀配置类
 *
 * @author summer
 * @date 2019-03-13 18:04
 * @version V1.0.0-RELEASE
 */
public interface MessageRedisKeyPrefixConstants {

    /**
     * 延迟发送缓存的 key 模板
     * 结构解释: easy:spring:message:delay:cache:{MessageChannelType}:{messageNo}
     */
    String MESSAGE_DELAY_CACHE_TEMPLATE = "easy:spring:message:delay:cache:%s:%s";
    /**
     * 延迟发送的 key 的前缀(到通道)
     */
    String MESSAGE_DELAY_CACHE_CHANNEL_PREFIX_TEMPLATE = "easy:spring:message:delay:cache:%s*";
}
