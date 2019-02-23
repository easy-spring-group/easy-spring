package io.easyspring.security.core.social.support;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 继承默认的社交登录配置，加入自定义的后处理逻辑
 *
 * @author summer
 * @date 2019-01-18 13:26
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class EasySpringSocialConfigurer extends SpringSocialConfigurer {
    /**
     * 第三方登录的拦截地址
     */
    private String filterProcessesUrl;

	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	public EasySpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

    /**
     * TODO
     *
     * @author summer
     * @date 2019-01-18 13:29
     * @param object
     * @return T
     * @version V1.0.0-RELEASE
     */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
	    // 获取 social 权限校验过滤器
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		// 设置拦截的地址
		filter.setFilterProcessesUrl(filterProcessesUrl);
		// 如果登录后的过滤处理器不为空, 则设置 social 的过滤器
		if (socialAuthenticationFilterPostProcessor != null) {
			socialAuthenticationFilterPostProcessor.process(filter);
		}
		// 返回封装好的过滤器对象
		return (T) filter;
	}
}
