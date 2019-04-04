package io.easyspring.message.core.subset.system;

import io.easyspring.message.core.support.EasyMessageReceiver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 系统消息接收者对象
 *
 * @author summer
 * DateTime 2019-03-15 15:02
 * @version V1.0.0-RELEASE
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SystemReceiver extends EasyMessageReceiver {

    private static final long serialVersionUID = 4325235591807634251L;

    /**
     * 系统消息接收者 id
     */
    @NotNull(message = "系统消息接收者不能为空")
    @Min(value = 0, message = "系统消息的接收者 id 不能小于 0")
    private Long receiver;
}
