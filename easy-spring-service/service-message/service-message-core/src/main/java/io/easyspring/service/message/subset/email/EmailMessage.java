package io.easyspring.service.message.subset.email;

import io.easyspring.service.message.support.EasyMessage;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 邮件消息实体
 *
 * @author summer
 * @date 2019-03-15 14:41
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmailMessage extends EasyMessage {

    private static final long serialVersionUID = -9081777351360668314L;

    /**
     * 接收者
     */
    @NotNull(message = "邮件接收者不能为空")
    @NotEmpty(message = "邮件接收者不能为空")
    @Valid
    private List<EmailReceiver> receiverList;

    /**
     * 邮件主题
     */
    @NotBlank(message = "邮件主题不能为空")
    private String subject;

    /**
     * 是否是 html 内容
     */
    private Boolean isHtml;
}
