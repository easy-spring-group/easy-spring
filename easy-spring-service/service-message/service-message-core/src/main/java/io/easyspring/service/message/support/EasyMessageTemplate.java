package io.easyspring.service.message.support;

import io.easyspring.service.message.MessageChannelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 消息模板
 *
 * @author summer
 * @date 2019-03-12 15:12
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EasyMessageTemplate implements Serializable {

    private static final long serialVersionUID = 3172716922568457460L;

    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板类型
     */
    @NotNull(message = "模板通道类型不能为空")
    private MessageChannelType channelType;
    /**
     * 消息类型(消息类型, 用于区分不同类型的模板)
     */
    @NotBlank(message = "消息类型不能为空")
    private String messageType;
    /**
     * 模板识别码
     */
    @NotBlank(message = "模板识别码不能为空")
    private String code;
    /**
     * 模板路径
     */
    private String path;
    /**
     * 消息模板内容
     */
    private String template;
    /**
     * 延时时长 (单位是秒)
     */
    private Integer delayTime;
}
