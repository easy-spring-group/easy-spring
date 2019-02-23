package io.easyspring.security.core.social.support;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * SocialAuthenticationFilter 后处理器，用于在不同环境下个性化社交登录的配置
 *
 * @author summer
 * @date 2019-01-18 13:15
 * @version V1.0.0-RELEASE
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * 个性化配置的方法
     * TODO 暂时还未确定具体作用
	 * @param socialAuthenticationFilter
	 */
	void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
