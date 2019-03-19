package io.easyspring.service.message.subset.email.impl;

import io.easyspring.service.message.EasyMessageException;
import io.easyspring.service.message.MessageChannelType;
import io.easyspring.service.message.properties.EmailMessageConstants;
import io.easyspring.service.message.properties.MessageProperties;
import io.easyspring.service.message.subset.email.EmailMessage;
import io.easyspring.service.message.subset.email.EmailMessageBuilder;
import io.easyspring.service.message.subset.email.EmailReceiver;
import io.easyspring.service.message.support.EasyMessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 默认的邮件的格式化类
 *
 * @author summer
 * @date 2019-03-15 14:48
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultEmailMessageBuilder implements EmailMessageBuilder {

    /**
     * 注入模板引擎
     */
    @Resource
    private TemplateEngine templateEngine;
    /**
     * 注入消息的配置
     */
    @Resource
    private MessageProperties messageProperties;

    /**
     * 格式化邮件的方法
     *
     * @param receiverList 消息接收者集合
     * @param easyMessageTemplate 邮件息消息模板
     * @param extend 需要替换的值
     * @return io.easyspring.service.message.subset.email.EmailMessage
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Override
    public EmailMessage builder(List<EmailReceiver> receiverList,
                                EasyMessageTemplate easyMessageTemplate,
                                Map<String, Object> extend) {

        log.info("[消息格式化] 邮件格式化, 需要格式化的模板是: {}", easyMessageTemplate);

        // 获取邮件内容
        String mailContent = processEmailContent(easyMessageTemplate.getTemplate(),
                easyMessageTemplate.getPath(), extend);
        // 获取邮件主题
        String emailSubject = getEmailSubject(easyMessageTemplate.getName(), extend);

        /*
         * 封装邮件消息对象
         */
        EmailMessage emailMessage = new EmailMessage();
        // 设置主题
        emailMessage.setSubject(emailSubject);
        // 设置接收者
        emailMessage.setReceiverList(receiverList);
        // 设置通道类型
        emailMessage.setChannelType(MessageChannelType.EMAIL);
        // 设置消息类型
        emailMessage.setMessageType(easyMessageTemplate.getMessageType());
        // 设置消息内容
        emailMessage.setContent(mailContent);
        // 设置消息相关数据
        emailMessage.setExtend(extend);
        // 设置消息的发送者
        emailMessage.setSender(messageProperties.getEmail().getSender());
        // 设置是否是 html 格式
        emailMessage.setIsHtml(getIsHtml(extend));

        return emailMessage;
    }

    /**
     * 获取是否是 html 类型的邮件
     *
     * @param extend 邮件发送时的数据
     * @return boolean
     * @author summer
     * @date 2019-03-15 19:24
     * @version V1.0.0-RELEASE
     */
    private boolean getIsHtml(Map<String, Object> extend) {
        // 获取默认的配置
        boolean isHtml = EmailMessageConstants.DEFAULT_IS_HTML;

        // 如果扩展数据不为空
        if (!CollectionUtils.isEmpty(extend)) {
            // 从参数中获取是否是 html
            Object extendIsHtml = extend.get(EmailMessageConstants.IS_HTML_KEY);
            // 如果对应的参数是 Boolean 类型
            if (extendIsHtml instanceof Boolean) {
                // 执行参数修改
                isHtml = (boolean) extendIsHtml;
            }
        }

        // 返回最终的配置参数
        return isHtml;
    }

    /**
     * 获取邮件主题的方法
     *
     * @param templateName 邮件的模板名称
     * @param extend 邮件内容生成时的扩展数据
     * @return java.lang.String
     * @author summer
     * @date 2019-03-15 17:03
     * @version V1.0.0-RELEASE
     */
    private String getEmailSubject(String templateName, Map<String, Object> extend) {
        // 设置默认的邮件主题
        String subject = EmailMessageConstants.DEFAULT_SUBJECT;

        // 则检查模板是否有名字, 如果有名字则替换默认主题
        if (!StringUtils.isEmpty(templateName)) {
            // 如果有名字, 就使用模板的名字作为主题
            subject = templateName;
        }

        // 如果有扩展数据传入, 并且扩展数据中有主题字段
        if (!CollectionUtils.isEmpty(extend)) {
            // 从扩展数据中获取主题字段
            Object extendSubject = extend.get(EmailMessageConstants.SUBJECT_KEY);
            // 如果扩展数据中的主题字段是 string 类型,
            if (extendSubject instanceof String) {
                // 使用扩展数据中的主题为邮件的主题
                subject = (String) extendSubject;
            }
        }

        // 返回最终的主题名称
        return subject;
    }

    /**
     * 封装邮件内容的方法
     *
     * @param templateContent 文字类型的消息模板内容
     * @param templatePath 邮件 html 的模板地址
     * @param extend 生成时需要替换的数据
     * @return java.lang.String
     * @author summer
     * @date 2019-03-15 16:46
     * @version V1.0.0-RELEASE
     */
    private String processEmailContent(String templateContent, String templatePath,
                                       Map<String, Object> extend) {
        /*
         * 邮件内容的生成
         */
        String mailContent = null;
        // 如果邮件内容模板存在, 则使用邮件内容模板生成
        if (!StringUtils.isEmpty(templateContent)) {
            // 执行邮件内容的生成
            mailContent = processContent(templateContent, extend);
        } else if (!StringUtils.isEmpty(templatePath)) {
            // 执行邮件内容的生成
            mailContent = processHtmlContent(templatePath, extend);
        } else {
            // 如果两个模板均为空, 则抛出异常
            throw new EasyMessageException("邮件内容模板和邮件页面模板至少一个不能为空");
        }
        return mailContent;
    }

    /**
     * 格式化内容, 替换 template 中的占位符为真实的数据
     *
     * @param template 内容模板
     * @param replaceValue 需要替换的数据
     * @return java.lang.String
     * @author summer
     * @date 2019-03-12 15:37
     * @version V1.0.0-RELEASE
     */
    private String processContent(String template, Map<String, Object> replaceValue) {
        // 定义取代器
        StrSubstitutor strSubstitutor = new StrSubstitutor(replaceValue);
        // 执行替换并返回
        return strSubstitutor.replace(template);
    }

    /**
     * 根据 thymeleaf 模板, 生成发送的邮件信息
     *
     * @param templatePath 模板地址
     * @param variables 替换的数据
     * @return java.lang.String
     * @author summer
     * @date 2019-03-15 16:27
     * @version V1.0.0-RELEASE
     */
    private String processHtmlContent(String templatePath, Map<String, Object> variables) {
        // 创建 context 对象, 用于模板数据的设定
        Context thymeleafContext = new Context();
        // 设置生成邮件内容时需要替换的数据
        thymeleafContext.setVariables(variables);

        // 执行邮件生成并返回数据
        return templateEngine.process(templatePath, thymeleafContext);
    }
}
