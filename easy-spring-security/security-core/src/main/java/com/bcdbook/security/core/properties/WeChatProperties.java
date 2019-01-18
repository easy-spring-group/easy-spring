package com.bcdbook.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 微信登录配置项
 *
 * @author summer
 * @date 2019-01-16 22:35
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatProperties extends EasySocialProperties {

	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 weChat。
	 */
	private String providerId = "weChat";
}
