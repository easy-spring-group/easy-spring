package io.easyspring.service.message.subset.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 系统消息接收者对象
 *
 * @author summer
 * @date 2019-03-15 15:02
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SystemReceiver implements Serializable {

    private static final long serialVersionUID = 4325235591807634251L;

    /**
     * 系统消息接收者 id
     */
    @NotNull(message = "系统消息接收者不能为空")
    @Min(value = 0, message = "系统消息的接收者 id 不能小于 0")
    private Long receiver;
}
