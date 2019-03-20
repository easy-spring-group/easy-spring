package io.easyspring.service.message.subset.email.impl;

import io.easyspring.service.message.EasyMessageException;
import io.easyspring.service.message.MessageSender;
import io.easyspring.service.message.subset.email.EmailMessage;
import io.easyspring.service.message.subset.email.EmailReceiver;
import io.easyspring.service.message.subset.email.FileAttachmentBuilder;
import io.easyspring.service.message.subset.email.InputStreamAttachmentBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;

/**
 * 邮件发送器的默认实现
 *
 * @author summer
 * @date 2019-03-13 13:28
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultEmailMessageSender implements MessageSender<EmailMessage> {

    /**
     * 注入邮件发送器
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 邮件发送方法
     *
     * @param message 邮件消息对象
     * @return io.easyspring.service.message.subset.email.EmailMessage
     * @author summer
     * @date 2019-03-13 13:27
     * @version V1.0.0-RELEASE
     */
    @Override
    public void send(EmailMessage message) {
        /*
         * 输出需要自己实现的提示
         */
        log.warn("[邮件发送] 默认的邮件发送器, 如需自定义请实现 EmailMessageSender 接口");
        log.info("[邮件发送] 将要发送的邮件消息是: {}", message);

        // 创建模拟消息对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 定义模拟消息工具类
        MimeMessageHelper helper = null;
        // 获取邮件的接收者
        String[] receiverArray = buildReceiverArray(message.getReceiverList());
        if (receiverArray == null || receiverArray.length < 1) {
            throw new EasyMessageException("邮件接收者不能为空");
        }

        // 获取是否是 html 形式的内容, 如果没有设置, 则默认是 html
        boolean isHtml = message.getIsHtml() == null ?
                true : message.getIsHtml();

        try {
            // 创建模拟消息工具类
            helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发送者
            helper.setFrom(message.getSender());
            // 设置接收者
            helper.setTo(receiverArray);
            // 设置主题
            helper.setSubject(message.getSubject());
            // 设置内容
            helper.setText(message.getContent(), isHtml);

            /*
             * 设置附件
             */
            // 设置文件类型的附件
            List<FileAttachmentBuilder> fileAttachmentBuilderList = message.getFileAttachmentBuilderList();
            // 如果文件类型的附件不为空, 则执行循环设置
            if (!CollectionUtils.isEmpty(fileAttachmentBuilderList)) {
                for (FileAttachmentBuilder fileAttachmentBuilder : fileAttachmentBuilderList){
                    helper.addAttachment(fileAttachmentBuilder.getName(), fileAttachmentBuilder.getFile());
                }
            }

            // 设置流类型的附件
            List<InputStreamAttachmentBuilder> inputStreamAttachmentBuilderList =
                    message.getInputStreamAttachmentBuilderList();
            // 如果文件流类型的附件不为空, 则循环执行设置
            if (!CollectionUtils.isEmpty(inputStreamAttachmentBuilderList)) {
                for (InputStreamAttachmentBuilder inputStreamAttachmentBuilder : inputStreamAttachmentBuilderList){
                    helper.addAttachment(inputStreamAttachmentBuilder.getName(),
                            inputStreamAttachmentBuilder.getInputStreamSource());
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new EasyMessageException("邮件发送出错");
        }

        // 执行真实的发送操作
        mailSender.send(mimeMessage);

        log.info("邮件发送成功, 邮件接收者: {}", Arrays.toString(receiverArray));
    }

    /**
     * 将对象类型的接收者转换成数组
     *
     * @param emailReceiverList 邮件接收者
     * @return java.lang.String[]
     * @author summer
     * @date 2019-03-15 18:14
     * @version V1.0.0-RELEASE
     */
    private String[] buildReceiverArray(List<EmailReceiver> emailReceiverList) {
        // 参数校验
        if (CollectionUtils.isEmpty(emailReceiverList)) {
            return null;
        }

        // 循环封装接收者
        return emailReceiverList.stream()
                .map(EmailReceiver::getReceiver)
                .toArray(stringReceiver -> new String[emailReceiverList.size()]);
    }
}
