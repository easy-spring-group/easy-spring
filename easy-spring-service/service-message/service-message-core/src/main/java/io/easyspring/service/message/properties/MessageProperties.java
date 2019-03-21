package io.easyspring.service.message.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 消息的配置参数类
 *
 * @author summer
 * DateTime 2019-03-12 16:26
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "easy-spring.message")
public class MessageProperties {

    /**
     * 消息延时发送的配置
     */
    private MessageDelayProperties delay = new MessageDelayProperties();

    /**
     * 邮件消息的配置
     */
    private EmailMessageProperties email = new EmailMessageProperties();
}
