package io.easyspring.service.message;

import io.easyspring.service.message.properties.MessageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 消息的配置类
 *
 * @author summer
 * @date 2019-03-12 16:26
 * @version V1.0.0-RELEASE
 */
@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class MessageConfig {
}