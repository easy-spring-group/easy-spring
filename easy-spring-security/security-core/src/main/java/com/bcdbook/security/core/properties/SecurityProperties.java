package com.bcdbook.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Security 的配置类
 *
 * @author summer
 * @date 2019-01-16 20:32
 * @version V1.0.0-RELEASE
 */
@ConfigurationProperties(prefix = SecurityPropertiesKeyConstants.SECURITY_PROPERTIES_KEY)
@NoArgsConstructor
@Data
public class SecurityProperties {

	/**
	 * 浏览器环境配置
	 */
	private BrowserProperties browser = new BrowserProperties();
	/**
	 * 验证码配置
	 */
	private ValidateCodeProperties code = new ValidateCodeProperties();
	/**
	 * 社交登录配置
	 */
	private SocialProperties social = new SocialProperties();
	/**
	 * OAuth2 认证服务器配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();
}

