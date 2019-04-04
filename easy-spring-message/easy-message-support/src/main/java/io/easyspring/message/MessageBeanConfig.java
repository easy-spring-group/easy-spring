package io.easyspring.message;

import io.easyspring.message.impl.DefaultEasyMessageTemplateServiceImpl;
import io.easyspring.message.impl.RedisMessageDelayCacheRepository;
import io.easyspring.message.properties.MessageConstants;
import io.easyspring.message.subset.email.EmailMessage;
import io.easyspring.message.subset.email.EmailMessageProcessor;
import io.easyspring.message.subset.email.EmailReceiver;
import io.easyspring.message.subset.email.impl.DefaultEmailMessageBuilder;
import io.easyspring.message.subset.email.impl.DefaultEmailMessageSender;
import io.easyspring.message.subset.sms.SmsMessage;
import io.easyspring.message.subset.sms.SmsMessageProcessor;
import io.easyspring.message.subset.sms.SmsReceiver;
import io.easyspring.message.subset.sms.impl.DefaultSmsMessageBuilder;
import io.easyspring.message.subset.sms.impl.DefaultSmsMessageSender;
import io.easyspring.message.subset.system.SystemMessage;
import io.easyspring.message.subset.system.SystemMessageProcessor;
import io.easyspring.message.subset.system.SystemReceiver;
import io.easyspring.message.subset.system.impl.DefaultSystemMessageBuilder;
import io.easyspring.message.subset.system.impl.DefaultSystemMessageSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息的 Bean 的配置类
 *
 * @author summer
 * DateTime 2019-03-12 16:26
 * @version V1.0.0-RELEASE
 */
@Configuration
public class MessageBeanConfig {

    /**
     * 消息处理器的控制器
     *
     * @return io.easyspring.service.message.MessageProcessorHolder
     * Author summer
     * DateTime 2019-03-19 21:55
     * Version V1.0.0-RELEASE
     */
    @Bean
    public MessageProcessorHolder messageProcessorHolder(){
        // 默认创建 Redis 的存储器
        return new MessageProcessorHolder();
    }

    /**
     * 延迟发送时缓存消息的存储器
     *
     * @return io.easyspring.service.message.MessageDelayCacheRepository
     * Author summer
     * DateTime 2019-03-13 17:18
     * Version V1.0.0-RELEASE
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
     * Author summer
     * DateTime 2019-03-13 13:19
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(EasyMessageTemplateService.class)
    public EasyMessageTemplateService easyMessageTemplateService(){
        // 返回默认的短信模板 service
        return new DefaultEasyMessageTemplateServiceImpl();
    }

    /**
     * 短信消息处理器的 Bean 的配置
     *
     * @return io.easyspring.service.message.MessageProcessor
     * Author summer
     * DateTime 2019-03-19 21:48
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.Sms.PROCESSOR_BEAN_NAME)
    public MessageProcessor smsMessageProcessor(){
        // 返回默认的短信生成器
        return new SmsMessageProcessor();
    }

    /**
     * 默认的短信消息的格式化类
     *
     * @return io.easyspring.service.message.subset.sms.impl.DefaultSmsMessageBuilder
     * Author summer
     * DateTime 2019-03-13 13:21
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.Sms.BUILDER_BEAN_NAME)
    public MessageBuilder<SmsMessage, SmsReceiver> smsMessageBuilder(){
        // 返回默认的短信生成器
        return new DefaultSmsMessageBuilder();
    }

    /**
     * 短信发送器的 Bean 配置
     *
     * @return io.easyspring.service.message.subset.sms.SmsMessageSender
     * Author summer
     * DateTime 2019-03-13 13:32
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.Sms.SENDER_BEAN_NAME)
    public MessageSender<SmsMessage> smsMessageSender(){
        // 返回默认的短信发送器
        return new DefaultSmsMessageSender();
    }

    /**
     * 邮件消息处理器的 Bean 的配置
     *
     * @return io.easyspring.service.message.MessageProcessor
     * Author summer
     * DateTime 2019-03-19 21:48
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.Email.PROCESSOR_BEAN_NAME)
    public MessageProcessor emailMessageProcessor(){
        // 返回默认的短信生成器
        return new EmailMessageProcessor();
    }

    /**
     * 默认的邮件消息的格式化类
     *
     * @return io.easyspring.service.message.subset.email.impl.DefaultEmailMessageBuilder
     * Author summer
     * DateTime 2019-03-15 14:48
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.Email.BUILDER_BEAN_NAME)
    public MessageBuilder<EmailMessage, EmailReceiver> emailMessageBuilder(){
        // 返回默认的邮件生成器
        return new DefaultEmailMessageBuilder();
    }

    /**
     * 邮件发送器的 Bean 配置
     *
     * @return io.easyspring.service.message.subset.email.EmailMessageSender
     * Author summer
     * DateTime 2019-03-15 14:48
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.Email.SENDER_BEAN_NAME)
    public MessageSender<EmailMessage> emailMessageSender(){
        // 返回默认的短信发送器
        return new DefaultEmailMessageSender();
    }

    /**
     * 系统消息处理器的 Bean 的配置
     *
     * @return io.easyspring.service.message.MessageProcessor
     * Author summer
     * DateTime 2019-03-19 21:48
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.System.PROCESSOR_BEAN_NAME)
    public MessageProcessor systemMessageProcessor(){
        // 返回默认的短信生成器
        return new SystemMessageProcessor();
    }

    /**
     * 默认的系统消息的格式化类
     *
     * @return io.easyspring.service.message.subset.system.impl.DefaultSystemMessageBuilder
     * Author summer
     * DateTime 2019-03-15 14:48
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.System.BUILDER_BEAN_NAME)
    public MessageBuilder<SystemMessage, SystemReceiver> systemMessageBuilder(){
        // 返回默认的系统消息生成器
        return new DefaultSystemMessageBuilder();
    }

    /**
     * 系统消息发送器的 Bean 配置
     *
     * @return io.easyspring.service.message.subset.system.SystemMessageSender
     * Author summer
     * DateTime 2019-03-15 14:48
     * Version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = MessageConstants.System.SENDER_BEAN_NAME)
    public MessageSender<SystemMessage> systemMessageSender(){
        // 返回默认的短信发送器
        return new DefaultSystemMessageSender();
    }
}
