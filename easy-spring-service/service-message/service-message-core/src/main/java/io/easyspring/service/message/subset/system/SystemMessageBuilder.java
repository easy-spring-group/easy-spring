package io.easyspring.service.message.subset.system;

import io.easyspring.service.message.support.EasyMessageTemplate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 系统消息的封装器
 *
 * @author summer
 * @date 2019-03-15 14:44
 * @version V1.0.0-RELEASE
 */
@Validated
public interface SystemMessageBuilder {

    /**
     * 封装用于发送的消息
     *
     * @param receiverList 接收者集合
     * @param easyMessageTemplate 模板对象
     * @param extend 需要替换的参数
     * @return M
     * @author summer
     * @date 2019-03-15 14:44
     * @version V1.0.0-RELEASE
     */
    SystemMessage builder(@NotNull(message = "系统消息接收者不能为空")
                          @NotEmpty(message = "系统消息接收者不能为空") List<SystemReceiver> receiverList,
                          @NotNull(message = "系统消息模板不能为空") @Valid EasyMessageTemplate easyMessageTemplate,
                          Map<String, Object> extend);
}
