package io.easyspring.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信验证码的配置
 *
 * @author summer
 * @date 2019-01-16 20:55
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class SmsCodeProperties {

	/**
	 * 验证码长度
	 */
	private int length = 6;
	/**
	 * 过期时间
	 */
	private int expireIn = 60;
	/**
	 * 要拦截的 url，多个 url 用逗号隔开，例如: /ant,/pattern/*
	 */
	private String url;

}
