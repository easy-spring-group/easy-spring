package io.easyspring.service.message.test.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 消息发送的表单
 *
 * @author summer
 * DateTime 2019-03-14 11:26
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageForm {

    /**
     * 消息通道
     */
    @NotNull(message = "消息通不能为空")
    private String messageChannelType;
    /**
     * 消息接收者
     */
    @NotNull(message = "消息接收者不能为空")
    @NotEmpty(message = "消息接收者不能为空")
    private List<String> receiverList;
    /**
     * 消息模板识别码
     */
    @NotBlank(message = "消息模板识别码不能为空")
    private String templateCode;
    /**
     * 消息参数
     */
    private Map<String, Object> extend;
    /**
     * 是否忽略延迟发送
     */
    private Boolean ignoreDelay;
}
