package io.easyspring.service.message.subset.sms;

import io.easyspring.framework.common.validator.Telephone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 短信接收者对象
 *
 * @author summer
 * @date 2019-03-15 15:40
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SmsReceiver implements Serializable {

    private static final long serialVersionUID = 57729359795524130L;

    /**
     * 短信接收者
     */
    @NotBlank(message = "短信接收者不能为空")
    @Telephone(message = "请输入正确的手机号")
    private String receiver;
}
