package io.easyspring.service.message.properties;

import lombok.Data;

/**
 * 消息延时发送的配置
 *
 * @author summer
 * DateTime 2019-03-13 16:25
 * @version V1.0.0-RELEASE
 */
@Data
public class MessageDelayProperties {

    /**
     * 消息延期发送时, 缓存的时长
     * 注意: 延期发送的时长要小于此时长, 否则消息就永远发不出去了
     */
    private int expire = 7200;
}
