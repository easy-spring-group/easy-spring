package io.easyspring.message;

import io.easyspring.message.properties.MessageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 消息的配置类
 *
 * @author summer
 * DateTime 2019-03-12 16:26
 * @version V1.0.0-RELEASE
 */
@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class MessageConfig {
}