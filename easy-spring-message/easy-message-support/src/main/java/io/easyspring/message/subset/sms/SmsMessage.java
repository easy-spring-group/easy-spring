package io.easyspring.message.subset.sms;

import io.easyspring.message.support.EasyMessage;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 短信消息实体类
 *
 * @author summer
 * DateTime 2019-03-12 15:00
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SmsMessage extends EasyMessage {

    private static final long serialVersionUID = 4431592224667621984L;

    /**
     * 接收者集合
     */
    @NotNull(message = "短信接收者不能为空")
    @NotEmpty(message = "短信接收者不能为空")
    @Valid
    private List<SmsReceiver> receiverList;
}
