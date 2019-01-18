package com.bcdbook.security.core.validate.code.sms;

/**
 * 短信验证码的发送器
 *
 * @author summer
 * @date 2019-01-17 13:53
 * @version V1.0.0-RELEASE
 */
public interface SmsCodeSender {

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
	void send(String mobile, String code);
}
