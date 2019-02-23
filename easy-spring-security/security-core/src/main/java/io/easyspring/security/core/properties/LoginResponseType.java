package io.easyspring.security.core.properties;

/**
 * 认证成功后的响应方式
 *
 * @author summer
 * @date 2019-01-16 20:41
 * @version V1.0.0-RELEASE
 */
public enum LoginResponseType {
	/**
	 * 跳转
	 */
	REDIRECT,
	/**
	 * 返回 json
	 */
	JSON

}
