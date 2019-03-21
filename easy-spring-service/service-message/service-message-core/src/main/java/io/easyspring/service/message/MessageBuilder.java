package io.easyspring.service.message;

import io.easyspring.service.message.support.EasyMessage;
import io.easyspring.service.message.support.EasyMessageReceiver;
import io.easyspring.service.message.support.EasyMessageTemplate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 消息的封装器
 *
 * @author summer
 * DateTime 2019-03-15 14:44
 * @version V1.0.0-RELEASE
 */
@Validated
public interface MessageBuilder<M extends EasyMessage, R extends EasyMessageReceiver> {

    /**
     * 封装用于发送的消息
     *
     * @param receiverList 接收者集合
     * @param easyMessageTemplate 模板对象
     * @param extend 需要替换的参数
     * @return M
     * Author summer
     * DateTime 2019-03-15 14:44
     * Version V1.0.0-RELEASE
     */
    M builder(@NotNull(message = "消息接收者不能为空")
                          @NotEmpty(message = "消息接收者不能为空") List<R> receiverList,
                          @NotNull(message = "消息模板不能为空") @Valid EasyMessageTemplate easyMessageTemplate,
                          Map<String, Object> extend);
}
