package com.bcdbook.security.core.social.support;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单的 social 用户详情信息
 *
 * @author summer
 * @date 2019-01-17 18:09
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class SocialUserInfo {

	private String providerId;
	
	private String providerUserId;
	
	private String nickname;
	
	private String headImg;
}
