package io.easyspring.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 * 在此只是做一个示例, 真实的业务场景, 需要用户自己实现
 *
 * @author summer
 * @date 2019-01-17 13:54
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    /**
     * 验证码的发送方法
     *
     * @author summer
     * @date 2019-01-17 13:54
     * @param mobile 接收验证码的手机号
     * @param code 验证码
     * @return void
     * @version V1.0.0-RELEASE
     */
	@Override
	public void send(String mobile, String code) {
		log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
        log.info("向手机: {}, 发送短信验证码: {}", mobile, code);
	}
}
