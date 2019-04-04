package io.easyspring.message.subset.system;

import io.easyspring.message.support.EasyMessage;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统消息实体
 *
 * @author summer
 * DateTime 2019-03-15 14:41
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SystemMessage extends EasyMessage {

    private static final long serialVersionUID = -5241582758157368752L;

    /**
     * 接收者
     */
    @NotNull(message = "系统消息接收者不能为空")
    @NotEmpty(message = "系统消息接收者不能为空")
    @Valid
    private List<SystemReceiver> receiverList;

    /**
     * 系统消息标题
     */
    @NotBlank(message = "系统消息标题不能为空")
    private String title;
}
