package io.easyspring.message.core.impl;

import io.easyspring.message.core.EasyMessageException;
import io.easyspring.message.core.MessageChannelType;
import io.easyspring.message.core.MessageDelayCacheRepository;
import io.easyspring.message.core.properties.MessageProperties;
import io.easyspring.message.core.properties.MessageRedisKeyPrefixConstants;
import io.easyspring.message.core.support.EasyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 消息延迟发送的缓存信息存储器 -- Redis 实现
 *
 * @author summer
 * DateTime 2019-03-13 17:17
 * @version V1.0.0-RELEASE
 * // TODO: 2019-03-13 Redis 缓存需要先指定配置
 */
@Slf4j
public class RedisMessageDelayCacheRepository<T extends EasyMessage> implements MessageDelayCacheRepository<T> {
    /**
     * 注入 Redis 的模板
     */
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    /**
     * 注入消息的配置
     */
    @Autowired
    private MessageProperties messageProperties;


    /**
     * 保存消息的方法
     *
     * @param message 消息对象
     * Author summer
     * DateTime 2019-03-13 17:26
     * Version V1.0.0-RELEASE
     */
    @Override
    public void save(T message) {
        log.info("[消息缓存] 消息已被缓存到 Redis 中");

        // 生成缓存的 key
        String key = buildKey(message.getChannelType(), message.getMessageNo());
        // 保存 message 信息到 Redis 中
        redisTemplate.opsForValue()
                .set(key, message, messageProperties.getDelay().getExpire(), TimeUnit.SECONDS);
    }

    /**
     * 获取缓存的消息的方法
     *
     * @param messageChannelType 消息通道的类型
     * @param messageNo 消息编号
     * @return T
     * Author summer
     * DateTime 2019-03-13 17:30
     * Version V1.0.0-RELEASE
     */
    @Override
    public T get(MessageChannelType messageChannelType, String messageNo) {

        // 生成缓存的 key
        String key = buildKey(messageChannelType, messageNo);
        // 查询并返回数据
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据消息通道, 获取通道下所有的消息
     *
     * @param messageChannelType 消息通道
     * @return java.util.List<T>
     * Author summer
     * DateTime 2019-03-14 10:16
     * Version V1.0.0-RELEASE
     */
    @Override
    public List<T> listDelayMessageByMessageChannelType(MessageChannelType messageChannelType) {
        // 获取当前通道下所有消息的 key
        Set<Object> messageChannelTypeKeySet = getKeySetByMessageChannelType(messageChannelType);
        // 如果没有对应的数据, 则直接返回空
        if (CollectionUtils.isEmpty(messageChannelTypeKeySet)) {
            return null;
        }

        // 根据通道下消息的 key 集合获取通道下所有的信息并返回
        List<Object> sourceMessageList = redisTemplate.opsForValue().multiGet(messageChannelTypeKeySet);
        // 校验获取到的缓存的消息
        if (CollectionUtils.isEmpty(sourceMessageList)) {
            return null;
        }

        return sourceMessageList.stream()
                .filter(Objects::nonNull)
                .map(message -> {
                    return (T)message;
                }).collect(Collectors.toList());
    }

    /**
     * 删除缓存的消息的方法
     *
     * @param messageChannelType 消息通道的类型
     * @param messageNo 消息编码
     * Author summer
     * DateTime 2019-03-13 17:31
     * Version V1.0.0-RELEASE
     */
    @Override
    public void remove(MessageChannelType messageChannelType, String messageNo) {
        // 获取缓存在 Redis 中的消息的 key
        String key = buildKey(messageChannelType, messageNo);
        // 执行删除操作
        redisTemplate.delete(key);
    }

    /**
     * 根据通道信息, 获取通道下所有数据的 key
     *
     * @param messageChannelType 消息通道类型
     * @return java.util.Set<java.lang.String>
     * Author summer
     * DateTime 2019-03-14 10:03
     * Version V1.0.0-RELEASE
     */
    private Set<Object> getKeySetByMessageChannelType(MessageChannelType messageChannelType){
        // 参数校验
        if (messageChannelType == null) {
            throw new EasyMessageException("消息通道类型不能为空");
        }

        // 获取缓存消息的前缀(精确到通道)
        String keyPrefix = buildKeyPrefix(messageChannelType);

        // 原始的 key
        return redisTemplate.keys(keyPrefix);

//        Set<Object> sourceKeySet = redisTemplate.keys(keyPrefix);
//        // 对返回数据进行校验, 如果为空, 则直接返回空
//        if (CollectionUtils.isEmpty(sourceKeySet)) {
//            return null;
//        }
//
//        // 执行转换, 把 Object key 转换成 String key. 并返回
//        return sourceKeySet.stream()
//                .map(key -> {
//                    return (String)key;
//                }).collect(Collectors.toSet());
    }

    /**
     * 封装缓存消息的前缀(精确到通道)
     *
     * @param messageChannelType 通道类型
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-14 10:07
     * Version V1.0.0-RELEASE
     */
    private String buildKeyPrefix(MessageChannelType messageChannelType) {
        // 参数校验
        if (messageChannelType == null) {
            throw new EasyMessageException("消息通道类型不能为空");
        }

        // 执行封装并返回
        return String.format(MessageRedisKeyPrefixConstants.MESSAGE_DELAY_CACHE_CHANNEL_PREFIX_TEMPLATE,
                messageChannelType);
    }

    /**
     * 封装延时发生是缓存的 key 的方法
     *
     * @param messageChannelType 消息通道类型
     * @param messageNo 消息编号
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-13 18:12
     * Version V1.0.0-RELEASE
     */
    private String buildKey(MessageChannelType messageChannelType, String messageNo) {
        if (messageChannelType == null || StringUtils.isEmpty(messageNo)) {
            throw new EasyMessageException("封装缓存的 key 出错, 消息通道: "
                    + messageChannelType + ", 消息编号: " + messageNo);
        }
        return String.format(MessageRedisKeyPrefixConstants.MESSAGE_DELAY_CACHE_TEMPLATE,
                messageChannelType, messageNo);
    }
}
