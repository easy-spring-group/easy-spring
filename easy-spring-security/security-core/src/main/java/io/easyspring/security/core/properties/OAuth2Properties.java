package io.easyspring.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OAuth2 授权的配置类
 *
 * @author summer
 * @date 2019-01-16 22:39
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class OAuth2Properties {

	/**
	 * 使用 jwt 时为 token 签名的秘钥
	 */
	private String jwtSigningKey = SecurityConstants.JWT_SIGNING_KEY;
	/**
	 * 客户端配置
	 */
	private OAuth2ClientProperties[] clients = {};
}
