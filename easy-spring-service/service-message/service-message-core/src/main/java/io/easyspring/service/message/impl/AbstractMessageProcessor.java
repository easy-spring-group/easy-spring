package io.easyspring.service.message.impl;

import io.easyspring.service.message.*;
import io.easyspring.service.message.support.EasyMessage;
import io.easyspring.service.message.support.EasyMessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 默认的抽象消息处理器
 *
 * @author summer
 * DateTime 2019-03-12 16:40
 * @version V1.0.0-RELEASE
 */
@Slf4j
@Validated
public abstract class AbstractMessageProcessor<M extends EasyMessage> implements MessageProcessor {

    /**
     * 消息模板的 service
     */
    @Autowired
    private EasyMessageTemplateService easyMessageTemplateService;
    /**
     * 注入消息延迟发送的缓存器
     */
    @Autowired
    private MessageDelayCacheRepository<M> messageDelayCacheRepository;

    /**
     * 消息的创建方法
     *
     * @param receiverList 接收者集合
     * @param templateCode 模板识别码
     * @param extend 需要替换的参数
     * @param ignoreDelay 是否忽略延时发送, 不填写的时候默认 false
     * @return String
     * Author summer
     * DateTime 2019-03-12 18:01
     * Version V1.0.0-RELEASE
     */
    @Override
    public String create(List<String> receiverList, String templateCode, Map<String, Object> extend, Boolean ignoreDelay) {
        /*
         * 获取模板对象
         */
        // 查询消息模板对象
        EasyMessageTemplate easyMessageTemplate = easyMessageTemplateService.getByCode(templateCode);
        // 如果消息模板对象为空, 则直接抛出异常
        if (easyMessageTemplate == null) {
            throw new EasyMessageException("未找到对应的消息模板. 模板识别码: " + templateCode);
        }

        // 封装用于发送的消息
        M message = builder(receiverList, easyMessageTemplate, extend);

        // 消息封装的后处理操作
        postBuilder(message, easyMessageTemplate.getDelayTime(), ignoreDelay);

        // 获取消息延迟发送的时长
        Long delayExpireTime = message.getDelayExpireTime();
        // 如果时长为空, 或时长小于 1, 则直接发送, 否则存入缓存中
        if (delayExpireTime == null || delayExpireTime < 1) {
            send(message);
        } else {
            cacheMessage(message);
        }

        return message.getMessageNo();
    }

    /**
     * 根据传入的消息通道和消息编码, 如果消息已经到了延期发送时间, 则执行发送操作
     *
     * @param messageChannelType 消息通道类型
     * @param messageNo 消息编码
     * @return int
     * Author summer
     * DateTime 2019-03-13 18:31
     * Version V1.0.0-RELEASE
     */
    @Override
    public int sendDelayMessageIfExpire(MessageChannelType messageChannelType, String messageNo) {
        // 定义消息发送的数量
        int sendCont = 0;

        // 从缓存中获取未发送的消息
        M message = messageDelayCacheRepository.get(messageChannelType, messageNo);
        // 如果消息不存在, 则直接返回 false
        if (message == null) {
            return sendCont;
        }

        // 执行消息发送(如果到期), 并返回发送的数量
        sendCont = sendDelayMessageIfExpire(message);

        // 发送过程没有问题, 则返回 true
        return sendCont;
    }

    /**
     * 发送通道下所有到发送期的消息
     *
     * @param messageChannelType 消息通道类型
     * @return int
     * Author summer
     * DateTime 2019-03-14 10:23
     * Version V1.0.0-RELEASE
     */
    @Override
    public int sendDelayMessagesIfExpire(MessageChannelType messageChannelType) {
        // 定义消息发送的数量
        int sendCont = 0;

        // 获取通道下所有延迟发送的消息
        List<M> messageList = messageDelayCacheRepository.listDelayMessageByMessageChannelType(messageChannelType);
        // 对返回的数据进行校验
        if (CollectionUtils.isEmpty(messageList)) {
            return sendCont;
        }

        // 循环执行发送
        for(M message : messageList) {
            // 发送后, 延迟的消息做累加处理
            sendCont += sendDelayMessageIfExpire(message);
        }

        // 返回总的发送数量
        return sendCont;
    }

    /**
     * 撤回未发送的消息的方法
     *
     * @param messageChannelType 消息通道类型
     * @param messageNo 消息编码
     * Author summer
     * DateTime 2019-03-14 09:44
     * Version V1.0.0-RELEASE
     */
    @Override
    public void recall(MessageChannelType messageChannelType, String messageNo){
        // 直接删除缓存中的消息
        messageDelayCacheRepository.remove(messageChannelType, messageNo);
    }

    /**
     * 封装用于发送的消息
     * 主要用于封装消息的发送数据及参数的替换
     *
     * @param receiverList 接收者集合
     * @param easyMessageTemplate 消息模板对象
     * @param extend 需要替换的参数
     * @return M
     * Author summer
     * DateTime 2019-03-12 18:01
     * Version V1.0.0-RELEASE
     */
    protected abstract M builder(@NotNull(message = "消息接收者不能为空")
                                 @NotEmpty(message = "消息接收者不能为空") List<String> receiverList,
                                 @NotBlank(message = "模板识别码不能为空") @Valid EasyMessageTemplate easyMessageTemplate,
                                 Map<String, Object> extend);

    /**
     * 消息 builder 的后处理方法, 用于处理消息的延迟发送操作
     * 主要用于封装延迟发送的相关操作
     *
     * @param message 消息对象
     * @param delayTime 延迟时长(单位是秒)
     * @param ignoreDelay 是否忽略延迟发送
     * Author summer
     * DateTime 2019-03-14 13:47
     * Version V1.0.0-RELEASE
     */
    protected void postBuilder(@NotNull(message = "消息对象不能为空") M message, Integer delayTime, Boolean ignoreDelay) {
        // 如果不传入忽略延迟表示, 则表示不忽略
        if (ignoreDelay == null) {
            // 设置为不忽略
            ignoreDelay = false;
        }

        // 如果忽略延时发送, 或者延迟时长为空
        if (ignoreDelay || delayTime == null || delayTime < 1) {
            // 设置延时的到期时间为 -1
            message.setDelayExpireTime(-1L);
            // 直接返回消息对象
            return;
        }

        /*
         * 设置消息的到期时间
         */
        // 获取当前系统时间
        long currentTimeMillis = System.currentTimeMillis();
        // 定义过期时间
        Long delayTimeLong = Long.valueOf(delayTime);
        // 获取过期时长的毫秒数
        delayTimeLong = delayTimeLong * 1000L;
        // 获取延迟发送的到期时间点
        Long delayExpireTime = delayTimeLong + currentTimeMillis;
        // 设置延期发送的到期时间
        message.setDelayExpireTime(delayExpireTime);

        /*
         * 设置消息编码
         */
        // 生成新的消息编码
        String messageNo = RandomStringUtils.randomNumeric(20);
        // 设置消息编码
        message.setMessageNo(messageNo);
    }

    /**
     * 消息发送器
     *
     * @param message 需要发送的消息
     * Author summer
     * DateTime 2019-03-12 18:01
     * Version V1.0.0-RELEASE
     */
    protected abstract void send(@NotNull(message = "消息对象不能为空") @Valid M message);

    /**
     * 保存消息到缓存中
     *
     * @param message 消息对象
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-14 13:59
     * Version V1.0.0-RELEASE
     */
    private void cacheMessage(@NotNull(message = "消息对象不能为空") @Valid M message){
        // 保存延迟发送的消息到 缓存中
        messageDelayCacheRepository.save(message);
    }

    /**
     * 如果延迟发送的消息到了延迟的时间, 则执行消息发送
     *
     * @param message 消息对象
     * @return int
     * Author summer
     * DateTime 2019-03-14 11:02
     * Version V1.0.0-RELEASE
     */
    private int sendDelayMessageIfExpire(M message){
        // 定义消息发送的数量
        int sendCont = 0;
        /*
         * 推迟时间判断
         */
        // 获取延时到期时间
        long delayExpireTime = message.getDelayExpireTime();
        // 获取当前系统时间
        long currentTimeMillis = System.currentTimeMillis();
        // 如果延迟设置的时间小于当前系统时间, 则说明已经到了发送时间了
        if (delayExpireTime <= currentTimeMillis) {
            log.info("[延时发送] 将要发送延时消息: {}", message);

            // 执行消息发送
            send(message);
            // 删除缓存中的信息
            messageDelayCacheRepository.remove(message.getChannelType(), message.getMessageNo());
            // 发送成功后, 发送的数量加一
            sendCont++;
        }
        // 返回消息发送的数量
        return sendCont;
    }
}
