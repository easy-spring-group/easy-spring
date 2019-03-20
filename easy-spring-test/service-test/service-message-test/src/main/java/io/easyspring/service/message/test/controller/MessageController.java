package io.easyspring.service.message.test.controller;

import io.easyspring.service.message.MessageChannelType;
import io.easyspring.service.message.MessageProcessorHolder;
import io.easyspring.service.message.properties.MessageConstants;
import io.easyspring.service.message.test.form.MessageForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.util.Map;

/**
 * 消息 前端控制器
 *
 * @author summer
 * @date 2019-02-23
 * @version V1.0.0-RELEASE
 */
@RestController
@RequestMapping("/message/message")
@Validated
@Slf4j
public class MessageController {

    /**
     * 注入消息处理器的管理器
     */
    @Resource
    private MessageProcessorHolder messageProcessorHolder;

    /**
     * 消息发送的接口
     *
     * @param messageForm 消息表单
     * @return java.lang.String
     * @author summer
     * @date 2019-03-14 11:29
     * @version V1.0.0-RELEASE
     */
    @PostMapping
    public String send(@RequestBody @Valid MessageForm messageForm){

        Map<String, Object> extend = messageForm.getExtend();
        File file = new File("/Users/summer/Desktop/risk_management.png");
        extend.put(MessageConstants.Email.FILE_ATTACHMENT_KEY, file);

        return messageProcessorHolder
                // 根据消息类型获取消息的处理器
                .findMessageProcessor(messageForm.getMessageChannelType())
                // 创建消息
                .create(messageForm.getReceiverList(),
                        messageForm.getTemplateCode(),
                        extend,
                        messageForm.getIgnoreDelay());
    }

    /**
     * 检测并发送缓存的消息
     *
     * @param messageChannelType 消息通道类型
     * @param messageNo 消息编号
     * @return java.lang.Integer
     * @author summer
     * @date 2019-03-14 11:36
     * @version V1.0.0-RELEASE
     */
    @PostMapping(value = "/delay/{messageChannelType}/{messageNo}")
    public Integer sendDelayMessageIfExpire(
            @PathVariable(name = "messageChannelType") MessageChannelType messageChannelType,
            @PathVariable(name = "messageNo") String messageNo){

        // 根据消息类型获取对应的消息处理器
        return messageProcessorHolder.findMessageProcessor(messageChannelType)
                // 执行延迟检查并发送
                .sendDelayMessageIfExpire(messageChannelType, messageNo);
    }

    /**
     * 检测并发送缓存的消息(根据通道检测)
     *
     * @param messageChannelType 消息通道类型
     * @return java.lang.Integer
     * @author summer
     * @date 2019-03-14 11:36
     * @version V1.0.0-RELEASE
     */
    @PostMapping(value = "/delay/{messageChannelType}")
    public Integer sendDelayMessagesIfExpire(
            @PathVariable(name = "messageChannelType") MessageChannelType messageChannelType){

        // 根据消息类型获取对应的消息处理器
        return messageProcessorHolder.findMessageProcessor(messageChannelType)
                // 执行延迟检查并发送
                .sendDelayMessagesIfExpire(messageChannelType);
    }


    /**
     * 检测并发送缓存的消息
     *
     * @param messageChannelType 消息通道类型
     * @param messageNo 消息编号
     * @return java.lang.Integer
     * @author summer
     * @date 2019-03-14 11:36
     * @version V1.0.0-RELEASE
     */
    @DeleteMapping(value = "/delay/{messageChannelType}/{messageNo}")
    public void recall(@PathVariable(name = "messageChannelType") MessageChannelType messageChannelType,
                          @PathVariable(name = "messageNo") String messageNo){

        // 根据消息类型获取对应的消息处理器
        messageProcessorHolder.findMessageProcessor(messageChannelType)
                // 执行延迟检查并发送
                .recall(messageChannelType, messageNo);
    }
}

