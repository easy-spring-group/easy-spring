package com.bcdbook.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社交登录配置项
 *
 * @author summer
 * @date 2019-01-16 22:31
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class SocialProperties {

	/**
	 * 社交登录功能拦截的 url
	 */
	private String filterProcessesUrl = SecurityConstants.SOCIAL_FILTER_PROCESSES_URL;

    /**
     * QQ 配置项
     */
	private QQProperties qq = new QQProperties();
    /**
     * 微信配置项
     */
	private WeChatProperties weChat = new WeChatProperties();

}
