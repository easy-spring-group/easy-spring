package io.easyspring.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * QQ登录配置项
 *
 * @author summer
 * @date 2019-01-16 22:34
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class QQProperties extends EasySocialProperties {

	/**
	 * 第三方 id，用来决定发起第三方登录的 url，默认是 qq。
	 */
	private String providerId = "qq";
}
