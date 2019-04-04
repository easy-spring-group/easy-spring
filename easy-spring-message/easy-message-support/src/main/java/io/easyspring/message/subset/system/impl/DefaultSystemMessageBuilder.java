package io.easyspring.message.subset.system.impl;

import io.easyspring.message.MessageBuilder;
import io.easyspring.message.properties.MessageConstants;
import io.easyspring.message.subset.system.SystemMessage;
import io.easyspring.message.subset.system.SystemReceiver;
import io.easyspring.message.support.EasyMessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 默认的系统消息的格式化类
 *
 * @author summer
 * DateTime 2019-03-15 14:48
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultSystemMessageBuilder implements MessageBuilder<SystemMessage, SystemReceiver> {

    /**
     * 格式化系统消息的方法
     *
     * @param receiverList 消息接收者集合
     * @param easyMessageTemplate 系统消息模板
     * @param extend 需要替换的值
     * @return io.easyspring.service.message.subset.system.SystemMessage
     * Author summer
     * DateTime 2019-03-15 14:48
     * Version V1.0.0-RELEASE
     */
    @Override
    public SystemMessage builder(List<SystemReceiver> receiverList,
                                 EasyMessageTemplate easyMessageTemplate,
                                 Map<String, Object> extend) {

        log.info("[消息格式化] 系统消息格式化, 需要格式化的模板是: {}", easyMessageTemplate);

        // 获取系统消息内容
        String content = processContent(easyMessageTemplate.getTemplate(), extend);

        /*
         * 封装系统消息对象
         */
        SystemMessage systemMessage = new SystemMessage();
        // 设置系统消息的接收者
        systemMessage.setReceiverList(receiverList);
        // 设置系统消息的标题
        systemMessage.setTitle(getTitle(easyMessageTemplate.getName(), content, extend));
        // 设置系统消息的发送者
        systemMessage.setSender(getSender(extend).toString());
        // 设置系统消息的通道类型
        systemMessage.setChannelType(easyMessageTemplate.getChannelType());
        // 设置系统消息的消息类型
        systemMessage.setMessageType(easyMessageTemplate.getMessageType());
        // 设置系统消息的内容
        systemMessage.setContent(content);
        // 设置系统消息的发送的数据
        systemMessage.setExtend(extend);

        return systemMessage;
    }

    /**
     * 获取系统消息标题的方法
     *
     * @param templateName 系统消息的模板名称
     * @param content 消息内容
     * @param extend 系统消息内容生成时的扩展数据
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-15 17:03
     * Version V1.0.0-RELEASE
     */
    private String getTitle(String templateName, String content, Map<String, Object> extend) {
        // 设置默认的系统消息标题
        String title = MessageConstants.System.DEFAULT_TITLE;

        // 则检查模板是否有名字, 如果有名字则替换默认标题
        if (!StringUtils.isEmpty(templateName)) {
            // 如果有名字, 就使用模板的名字作为标题
            title = templateName;
        }

        // 如果内容中有数据, 则从内容中截取一部分, 作为标题
        if (!StringUtils.isEmpty(content)) {
            // 内容截取的结束点(设置默认长度为 20 个字符)
            int endIndex = 20;
            // 如果内容的长度大于默认的长度, 则使用默认长度, 否则使用内容长度
            endIndex = content.length() > endIndex ? endIndex : content.length();

            // 截取内容作为 title
            title = content.substring(0, endIndex);
        }


        // 如果有扩展数据传入, 并且扩展数据中有标题字段
        if (!CollectionUtils.isEmpty(extend)) {
            // 从扩展数据中获取标题字段
            Object extendTitle = extend.get(MessageConstants.System.TITLE_KEY);
            // 如果扩展数据中的标题字段是 string 类型,
            if (extendTitle instanceof String) {
                // 使用扩展数据中的标题为系统消息的标题
                title = (String) extendTitle;
            }
        }

        // 返回最终的标题名称
        return title;
    }

    /**
     * 获取系统消息发送者的方法
     *
     * @param extend 系统消息内容生成时的扩展数据
     * @return java.lang.Long
     * Author summer
     * DateTime 2019-03-15 17:03
     * Version V1.0.0-RELEASE
     */
    private Long getSender(Map<String, Object> extend) {
        // 设置默认的系统消息发送者
        Long sender = MessageConstants.System.DEFAULT_SENDER;

        // 如果有扩展数据传入, 并且扩展数据中有发送者字段
        if (!CollectionUtils.isEmpty(extend)) {
            // 从扩展数据中获取发送者字段
            Object extendSender = extend.get(MessageConstants.System.SENDER_KEY);
            // 如果扩展数据中的发送者字段是 string 类型,
            if (extendSender instanceof Long) {
                // 使用扩展数据中的发送者为系统消息的发送者
                sender = (Long) extendSender;
            }
        }

        // 返回最终的发送者名称
        return sender;
    }

    /**
     * 格式化内容, 替换 template 中的占位符为真实的数据
     *
     * @param template 内容模板
     * @param replaceValue 需要替换的数据
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-12 15:37
     * Version V1.0.0-RELEASE
     */
    private String processContent(String template, Map<String, Object> replaceValue) {
        // 定义取代器
        StrSubstitutor strSubstitutor = new StrSubstitutor(replaceValue);
        // 执行替换并返回
        return strSubstitutor.replace(template);
    }
}
