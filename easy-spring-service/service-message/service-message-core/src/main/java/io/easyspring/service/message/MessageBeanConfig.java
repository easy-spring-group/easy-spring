package io.easyspring.service.message;

import io.easyspring.service.message.impl.DefaultEasyMessageTemplateServiceImpl;
import io.easyspring.service.message.impl.RedisMessageDelayCacheRepository;
import io.easyspring.service.message.subset.email.EmailMessageBuilder;
import io.easyspring.service.message.subset.email.EmailMessageSender;
import io.easyspring.service.message.subset.email.impl.DefaultEmailMessageBuilder;
import io.easyspring.service.message.subset.email.impl.DefaultEmailMessageSender;
import io.easyspring.service.message.subset.sms.SmsMessageBuilder;
import io.easyspring.service.message.subset.sms.SmsMessageSender;
import io.easyspring.service.message.subset.sms.impl.DefaultSmsMessageBuilder;
import io.easyspring.service.message.subset.sms.impl.DefaultSmsMessageSender;
import io.easyspring.service.message.subset.system.SystemMessageBuilder;
import io.easyspring.service.message.subset.system.SystemMessageSender;
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
     * @return com.yinbaochina.management.risk.message.manage.MessageDelayCacheRepository
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
     * @return com.yinbaochina.management.risk.message.manage.EasyMessageTemplateService
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
     * @return com.yinbaochina.management.risk.message.manage.subset.sms.impl.DefaultSmsMessageBuilder
     * @author summer
     * @date 2019-03-13 13:21
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(SmsMessageBuilder.class)
    public SmsMessageBuilder smsMessageBuilder(){
        // 返回默认的短信生成器
        return new DefaultSmsMessageBuilder();
    }

    /**
     * 短信发送器的 Bean 配置
     *
     * @return com.yinbaochina.management.risk.message.manage.subset.sms.SmsMessageSender
     * @author summer
     * @date 2019-03-13 13:32
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(SmsMessageSender.class)
    public SmsMessageSender smsMessageSender(){
        // 返回默认的短信发送器
        return new DefaultSmsMessageSender();
    }

    /**
     * 默认的邮件消息的格式化类
     *
     * @return com.yinbaochina.management.risk.message.manage.subset.email.impl.DefaultEmailMessageBuilder
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(EmailMessageBuilder.class)
    public EmailMessageBuilder emailMessageBuilder(){
        // 返回默认的邮件生成器
        return new DefaultEmailMessageBuilder();
    }

    /**
     * 邮件发送器的 Bean 配置
     *
     * @return com.yinbaochina.management.risk.message.manage.subset.email.EmailMessageSender
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(EmailMessageSender.class)
    public EmailMessageSender emailMessageSender(){
        // 返回默认的短信发送器
        return new DefaultEmailMessageSender();
    }

    /**
     * 默认的系统消息的格式化类
     *
     * @return com.yinbaochina.management.risk.message.manage.subset.system.impl.DefaultSystemMessageBuilder
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(SystemMessageBuilder.class)
    public SystemMessageBuilder systemMessageBuilder(){
        // 返回默认的系统消息生成器
        return new DefaultSystemMessageBuilder();
    }

    /**
     * 系统消息发送器的 Bean 配置
     *
     * @return com.yinbaochina.management.risk.message.manage.subset.system.SystemMessageSender
     * @author summer
     * @date 2019-03-15 14:48
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(SystemMessageSender.class)
    public SystemMessageSender systemMessageSender(){
        // 返回默认的短信发送器
        return new DefaultSystemMessageSender();
    }
}
