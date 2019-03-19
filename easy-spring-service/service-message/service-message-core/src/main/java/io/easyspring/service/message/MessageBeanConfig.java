package io.easyspring.service.message;

import io.easyspring.service.message.impl.DefaultEasyMessageTemplateServiceImpl;
import io.easyspring.service.message.impl.RedisMessageDelayCacheRepository;
import io.easyspring.service.message.subset.email.EmailMessage;
import io.easyspring.service.message.subset.email.EmailReceiver;
import io.easyspring.service.message.subset.email.impl.DefaultEmailMessageBuilder;
import io.easyspring.service.message.subset.email.impl.DefaultEmailMessageSender;
import io.easyspring.service.message.subset.sms.SmsMessage;
import io.easyspring.service.message.subset.sms.SmsReceiver;
import io.easyspring.service.message.subset.sms.impl.DefaultSmsMessageBuilder;
import io.easyspring.service.message.subset.sms.impl.DefaultSmsMessageSender;
import io.easyspring.service.message.subset.system.SystemMessage;
import io.easyspring.service.message.subset.system.SystemReceiver;
import io.easyspring.service.message.subset.system.impl.DefaultSystemMessageBuilder;
import io.easyspring.service.message.subset.system.impl.DefaultSystemMessageSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息的 Bean 的配置类
 *
 * @author summer
 * @date 2019-03-12 16:26
 * @version V1.0.0-RELEASE
 */
@Configuration
public class MessageBeanConfig {


    /**
     * 延迟发送时缓存消息的存储器
     *
     * @return io.easyspring.service.message.MessageDelayCacheRepository
     * @author summer
     * @date 2019-03-13 17:18
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(MessageDelayCacheRepository.class)
    public MessageDelayCacheRepository messageDelayCacheRepository(){
        // 默认创建 Redis 的存储器
        return new RedisMessageDelayCacheRepository();
    }

    /**
     * 默认的消息模板的 service 实现的 bean
     *
     * @return io.easyspring.service.message.EasyMessageTemplateService
     * @author summer
     * @date 2019-03-13 13:19
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(EasyMessageTemplateService.class)
    public EasyMessageTemplateService easyMessageTemplateService(){
        // 返回默认的短信模板 service
        return new DefaultEasyMessageTemplateServiceImpl();
    }

    /**
     * 默认的短信消息的格式化类
     *
     * @return io.easyspring.service.message.subset.sms.impl.DefaultSmsMessageBuilder
     * @author summer
     * @date 2019-03-13 13:21
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsMessageBuilder")
    public MessageBuilder<SmsMessage, SmsReceiver> smsMessageBuilder(){
        // 返回默认的短信生成器
        return new DefaultSmsMessageBuilder();
    }

    /**
     * 短信发送器的 Bean 配置
     *
     * @return io.easyspring.service.message.subset.sms.SmsMessageSender
     * @author summer
     * @date 2019-03-13 13:32
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsMessageSender")
    public MessageSender<SmsMessage> smsMessageSender(){
        // 返回默认的短信发送器
        return new DefaultSmsMessageSender();
    }

    /**
     * 默认的邮件消息的格式化类
     *
     * @return io.easyspring.service.message.subset.email.impl.DefaultEmailMessageBuilder
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "emailMessageBuilder")
    public MessageBuilder<EmailMessage, EmailReceiver> emailMessageBuilder(){
        // 返回默认的邮件生成器
        return new DefaultEmailMessageBuilder();
    }

    /**
     * 邮件发送器的 Bean 配置
     *
     * @return io.easyspring.service.message.subset.email.EmailMessageSender
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "emailMessageSender")
    public MessageSender<EmailMessage> emailMessageSender(){
        // 返回默认的短信发送器
        return new DefaultEmailMessageSender();
    }

    /**
     * 默认的系统消息的格式化类
     *
     * @return io.easyspring.service.message.subset.system.impl.DefaultSystemMessageBuilder
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "systemMessageBuilder")
    public MessageBuilder<SystemMessage, SystemReceiver> systemMessageBuilder(){
        // 返回默认的系统消息生成器
        return new DefaultSystemMessageBuilder();
    }

    /**
     * 系统消息发送器的 Bean 配置
     *
     * @return io.easyspring.service.message.subset.system.SystemMessageSender
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "systemMessageSender")
    public MessageSender<SystemMessage> systemMessageSender(){
        // 返回默认的短信发送器
        return new DefaultSystemMessageSender();
    }
}
