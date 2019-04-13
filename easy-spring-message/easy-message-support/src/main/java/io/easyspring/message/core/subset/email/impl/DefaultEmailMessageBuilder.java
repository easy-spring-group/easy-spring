package io.easyspring.message.core.subset.email.impl;

import io.easyspring.message.core.EasyMessageException;
import io.easyspring.message.core.MessageBuilder;
import io.easyspring.message.core.MessageChannelType;
import io.easyspring.message.core.properties.MessageProperties;
import io.easyspring.message.core.subset.email.EmailReceiver;
import io.easyspring.message.core.subset.email.FileAttachmentBuilder;
import io.easyspring.message.core.subset.email.InputStreamAttachmentBuilder;
import io.easyspring.message.core.support.EasyMessageTemplate;
import io.easyspring.message.core.properties.MessageConstants;
import io.easyspring.message.core.subset.email.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.springframework.core.io.InputStreamSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 默认的邮件的格式化类
 *
 * @author summer
 * DateTime 2019-03-15 14:48
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultEmailMessageBuilder implements MessageBuilder<EmailMessage, EmailReceiver> {

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
     * Author summer
     * DateTime 2019-03-15 14:48
     * Version V1.0.0-RELEASE
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
        // 设置文件类型的附件
        emailMessage.setFileAttachmentBuilderList(builderFileAttachmentList(extend));
        // 设置流类型的附件
        emailMessage.setInputStreamAttachmentBuilderList(builderInputStreamAttachmentList(extend));
        // 设置消息相关数据
        emailMessage.setExtend(extend);
        // 设置消息的发送者
        emailMessage.setSender(messageProperties.getEmail().getSender());
        // 设置是否是 html 格式
        emailMessage.setIsHtml(getIsHtml(extend));
        // 设置消息模板识别码
        emailMessage.setTemplateCode(easyMessageTemplate.getTemplateCode());

        return emailMessage;
    }

    /**
     * 封装附件信息
     *
     * @param extend 传入的数据对象
     * @return java.util.List<io.easyspring.service.message.subset.email.AttachmentBuilder>
     * Author summer
     * DateTime 2019-03-20 14:40
     * Version V1.0.0-RELEASE
     */
    private List<FileAttachmentBuilder> builderFileAttachmentList(Map<String, Object> extend){
        // 参数校验
        if (CollectionUtils.isEmpty(extend)) {
            return null;
        }

        // 获取文件对象
        Object attachmentObject = extend.get(MessageConstants.Email.FILE_ATTACHMENT_KEY);
        // 参数校验
        if (attachmentObject == null) {
            return null;
        }

        // 创建附件对象集合
        List<FileAttachmentBuilder> attachmentBuilderList = null;
        // 如果是集合
        if (attachmentObject instanceof List) {
            // 转换成集合
            List<Object> attachmentObjectList = (List<Object>) attachmentObject;
            // 循环转换其中的每一个附件对象
            attachmentBuilderList = attachmentObjectList.stream()
                    // 过滤掉空值
                    .filter(Objects::nonNull)
                    // 执行转换
                    .map(this::builderFileAttachment)
                    // 过滤掉转换后的空值
                    .filter(Objects::nonNull)
                    // 封装成集合
                    .collect(Collectors.toList());
        }

        // 执行单个的转换
        FileAttachmentBuilder fileAttachmentBuilder = builderFileAttachment(attachmentObject);
        // 如果可以转换成单个附件对象
        if (fileAttachmentBuilder != null) {
            // 创建返回对象
            attachmentBuilderList = new ArrayList<>();
            // 设置返回值
            attachmentBuilderList.add(fileAttachmentBuilder);
        }

        return attachmentBuilderList;
    }

    /**
     * 封装单个文件类型的附件信息
     *
     * @param attachment 附件信息对象(可以是 file 对象, 也可以是 AttachmentBuilder 对象)
     * @return io.easyspring.service.message.subset.email.AttachmentBuilder
     * Author summer
     * DateTime 2019-03-20 14:45
     * Version V1.0.0-RELEASE
     */
    private FileAttachmentBuilder builderFileAttachment(Object attachment) {
        // 参数校验
        if (attachment == null) {
            return null;
        }

        // 定义用于返回的附件对象
        FileAttachmentBuilder attachmentBuilderResult = null;

        // 如果是文件类型
        if (attachment instanceof File) {
            // 执行类型转换
            File fileAttachment = (File) attachment;

            // 创建返回数据对象, 并封装
            attachmentBuilderResult = new FileAttachmentBuilder();
            // 设置名称
            attachmentBuilderResult.setName(fileAttachment.getName());
            // 设置文件对象
            attachmentBuilderResult.setFile(fileAttachment);

            // 如果是附件对象, 则直接进行数据类型的转换
        } else if (attachment instanceof FileAttachmentBuilder) {
            attachmentBuilderResult = (FileAttachmentBuilder) attachment;
        }

        return attachmentBuilderResult;
    }

    /**
     * 封装流类型的附件
     *
     * @param extend 传入参数
     * @return java.util.List<io.easyspring.service.message.subset.email.InputStreamAttachmentBuilder>
     * Author summer
     * DateTime 2019-03-20 15:48
     * Version V1.0.0-RELEASE
     */
    private List<InputStreamAttachmentBuilder> builderInputStreamAttachmentList(Map<String, Object> extend){
        // 参数校验
        if (CollectionUtils.isEmpty(extend)) {
            return null;
        }

        // 获取文件对象
        Object sourceAttachmentObject = extend.get(MessageConstants.Email.INPUT_STREAM_ATTACHMENT_KEY);
        // 参数校验
        if (sourceAttachmentObject == null) {
            return null;
        }

        // 创建附件对象集合
        List<InputStreamAttachmentBuilder> attachmentBuilderList = null;
        // 如果是集合
        if (sourceAttachmentObject instanceof List) {

            // 转换成集合
            List<Object> sourceAttachmentObjectList = (List<Object>) sourceAttachmentObject;
            // 如果数据不为空
            if (!CollectionUtils.isEmpty(sourceAttachmentObjectList)) {
                // 过滤掉空值
                sourceAttachmentObjectList = sourceAttachmentObjectList.stream()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
            // 如果去除空值后数据为空, 则直接返回 null
            if (CollectionUtils.isEmpty(sourceAttachmentObjectList)) {
                return null;
            }

            // 定义附件的数量
            int size = 1;
            // 创建转换后的附件对象集合
            attachmentBuilderList = new ArrayList<>();
            // 执行转换
            for(Object attachmentObject : sourceAttachmentObjectList){
                // 定义附件的名称
                String attachmentName = MessageConstants.Email.DEFAULT_ATTACHMENT_NAME_PREFIX + size;
                // 执行转换
                InputStreamAttachmentBuilder inputStreamAttachmentBuilder =
                        builderInputStreamAttachment(attachmentName, attachmentObject);

                // 如果转换后数据不为空, 则加入到转换后的附件对象中
                if (inputStreamAttachmentBuilder != null) {
                    attachmentBuilderList.add(inputStreamAttachmentBuilder);
                }
            }
        }

        // 执行单个附件的转换
        InputStreamAttachmentBuilder inputStreamAttachmentBuilder = builderInputStreamAttachment(
                MessageConstants.Email.DEFAULT_ATTACHMENT_NAME_PREFIX + "1",
                sourceAttachmentObject);
        // 如果可以转换成流类型的附件
        if (inputStreamAttachmentBuilder != null) {
            // 创建转换后的附件对象集合
            attachmentBuilderList = new ArrayList<>();
            // 设置值
            attachmentBuilderList.add(inputStreamAttachmentBuilder);
        }

        return attachmentBuilderList;
    }

    /**
     * 转换单个流类型的附件对象
     *
     * @param defaultName 默认名称
     * @param attachment 附件对象
     * @return io.easyspring.service.message.subset.email.InputStreamAttachmentBuilder
     * Author summer
     * DateTime 2019-03-20 15:26
     * Version V1.0.0-RELEASE
     */
    private InputStreamAttachmentBuilder builderInputStreamAttachment(String defaultName, Object attachment) {
        // 参数校验
        if (attachment == null) {
            return null;
        }

        // 定义用于返回的附件对象
        InputStreamAttachmentBuilder attachmentBuilderResult = null;

        // 如果是文件类型
        if (attachment instanceof InputStreamSource) {
            // 执行类型转换
            InputStreamSource inputStreamAttachment = (InputStreamSource) attachment;

            // 创建返回数据对象, 并封装
            attachmentBuilderResult = new InputStreamAttachmentBuilder();
            // 设置名称
            attachmentBuilderResult.setName(defaultName);
            // 设置文件对象
            attachmentBuilderResult.setInputStreamSource(inputStreamAttachment);

            // 如果是附件对象, 则直接进行数据类型的转换
        } else if (attachment instanceof InputStreamAttachmentBuilder) {
            attachmentBuilderResult = (InputStreamAttachmentBuilder) attachment;
        }

        return attachmentBuilderResult;
    }


    /**
     * 获取是否是 html 类型的邮件
     *
     * @param extend 邮件发送时的数据
     * @return boolean
     * Author summer
     * DateTime 2019-03-15 19:24
     * Version V1.0.0-RELEASE
     */
    private boolean getIsHtml(Map<String, Object> extend) {
        // 获取默认的配置
        boolean isHtml = MessageConstants.Email.DEFAULT_IS_HTML;

        // 如果扩展数据不为空
        if (!CollectionUtils.isEmpty(extend)) {
            // 从参数中获取是否是 html
            Object extendIsHtml = extend.get(MessageConstants.Email.IS_HTML_KEY);
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
     * Author summer
     * DateTime 2019-03-15 17:03
     * Version V1.0.0-RELEASE
     */
    private String getEmailSubject(String templateName, Map<String, Object> extend) {
        // 设置默认的邮件主题
        String subject = MessageConstants.Email.DEFAULT_SUBJECT;

        // 则检查模板是否有名字, 如果有名字则替换默认主题
        if (!StringUtils.isEmpty(templateName)) {
            // 如果有名字, 就使用模板的名字作为主题
            subject = templateName;
        }

        // 如果有扩展数据传入, 并且扩展数据中有主题字段
        if (!CollectionUtils.isEmpty(extend)) {
            // 从扩展数据中获取主题字段
            Object extendSubject = extend.get(MessageConstants.Email.SUBJECT_KEY);
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
     * Author summer
     * DateTime 2019-03-15 16:46
     * Version V1.0.0-RELEASE
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

    /**
     * 根据 thymeleaf 模板, 生成发送的邮件信息
     *
     * @param templatePath 模板地址
     * @param variables 替换的数据
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-15 16:27
     * Version V1.0.0-RELEASE
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
