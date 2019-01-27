package com.bcdbook.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 认证服务器注册的第三方应用配置项
 *
 * @author summer
 * @date 2019-01-17 13:25
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class OAuth2ClientProperties {

	/**
	 * 第三方应用 appId
	 */
	private String clientId;
	/**
	 * 第三方应用 appSecret
	 */
	private String clientSecret;

    private String[] authorizedGrantTypes = {};
    // 信任的回调域
    private String[] redirectUris = {};
    private String[] scopes = {};

	/**
	 * 针对此应用发出的 token 的有效时间
	 */
	private int accessTokenValidateSeconds = 7200;
}
