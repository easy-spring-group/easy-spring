package io.easyspring.message.properties;

import lombok.Data;

/**
 * 邮件消息的配置类
 *
 * @author summer
 * DateTime 2019-03-15 18:05
 * @version V1.0.0-RELEASE
 */
@Data
public class EmailMessageProperties {

    /**
     * 发送的主机
     */
    private String host;
    /**
     * 发送者
     */
    private String sender;

}
