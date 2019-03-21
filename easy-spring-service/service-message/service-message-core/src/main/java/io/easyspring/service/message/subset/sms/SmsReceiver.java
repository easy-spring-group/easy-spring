package io.easyspring.service.message.subset.sms;

import io.easyspring.framework.common.validator.Telephone;
import io.easyspring.service.message.support.EasyMessageReceiver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 短信接收者对象
 *
 * @author summer
 * DateTime 2019-03-15 15:40
 * @version V1.0.0-RELEASE
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SmsReceiver extends EasyMessageReceiver {

    private static final long serialVersionUID = 57729359795524130L;

    /**
     * 短信接收者
     */
    @NotBlank(message = "短信接收者不能为空")
    @Telephone(message = "请输入正确的手机号")
    private String receiver;
}
