package io.easyspring.service.message.support;

import io.easyspring.service.message.MessageChannelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * 消息的基类
 *
 * @author summer
 * @date 2019-03-12 14:17
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EasyMessage implements Serializable {

    private static final long serialVersionUID = 8660995442706610746L;

    /**
     * 发送者
     */
    @NotBlank(message = "消息发送者不能为空")
    private String sender;
    /**
     * 消息发送方式的类型
     */
    @NotNull(message = "消息发送方式的类型不能为空")
    private MessageChannelType channelType;
    /**
     * 消息类型(通知类, 广告类, 骚扰类)
     */
    @NotBlank(message = "消息类型不能为空")
    private String messageType;
    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空")
    private String content;
    /**
     * 延时发送的到期时间
     */
    @NotNull(message = "延时发送的到期时间不能为空")
    private Long delayExpireTime = 0L;
    /**
     * 消息编号
     */
    private String messageNo;
    /**
     * 需要替换的参数
     */
    private Map<String, Object> extend;
}
