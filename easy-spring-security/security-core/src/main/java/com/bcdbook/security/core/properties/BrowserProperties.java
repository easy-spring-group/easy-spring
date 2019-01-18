package com.bcdbook.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 浏览器环境配置项
 *
 * @author summer
 * @date 2019-01-16 20:34
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class BrowserProperties {

	/**
	 * session 管理配置项
	 */
	private SessionProperties session = new SessionProperties();
	/**
	 * 登录页面，当引发登录行为的 url 以 html 或请求类型为 text/html 时，
     * 会跳到这里配置的 url 上
	 */
	private String signInPage = SecurityConstants.SIGN_IN_PAGE_URL;
	/**
	 * 退出成功时跳转的 url，如果配置了，则跳到指定的url，
     * 如果没配置，则返回 json 数据。
	 */
	private String signOutUrl;
	/**
	 * 社交登录，如果需要用户注册，跳转的页面
	 */
	private String signUpUrl = SecurityConstants.SOCIAL_SIGN_UP_URL;
	/**
	 * 登录响应的方式，默认是 json
	 */
	private LoginResponseType signInResponseType = LoginResponseType.JSON;
	/**
	 * '记住我'功能的有效时间，默认 1 周
	 */
	private int rememberMeSeconds = 604800;
	/**
	 * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
	 * 只在 signInResponseType 为 REDIRECT 时生效
	 */
	private String singInSuccessUrl;
}
