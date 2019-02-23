package io.easyspring.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * session 管理相关配置项
 *
 * @author summer
 * @date 2019-01-16 20:35
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class SessionProperties {

	/**
	 * 同一个用户在系统中的最大 session 数，默认 1
	 */
	private int maximumSessions = 1;
	/**
	 * 达到最大 session 时是否阻止新的登录请求，默认为 false，不阻止，
     * 新的登录会将老的登录失效掉
	 */
	private boolean maxSessionsPreventsLogin = false;
	/**
	 * session 失效时跳转的地址
	 */
	private String sessionInvalidUrl = SecurityConstants.SESSION_INVALID_URL;
}
