/**
 * 
 */
package com.bcdbook.security.browser;

import com.bcdbook.security.browser.logout.EasyLogoutSuccessHandler;
import com.bcdbook.security.browser.session.EasyExpiredSessionStrategy;
import com.bcdbook.security.browser.session.EasyInvalidSessionStrategy;
import com.bcdbook.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * 浏览器环境下扩展点配置，配置在这里的 bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 *
 * @author summer
 * @date 2019-01-17 13:03
 * @version V1.0.0-RELEASE
 */
@Configuration
public class BrowserSecurityBeanConfig {

    /**
     * 注入自定义的安全配置
     */
	@Autowired
	private SecurityProperties securityProperties;
	
    /**
     * session 失效时的处理策略配置
     *
     * @author summer
     * @date 2019-01-17 13:06
     * @return org.springframework.security.web.session.InvalidSessionStrategy
     * @version V1.0.0-RELEASE
     */
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new EasyInvalidSessionStrategy(securityProperties);
	}
	
    /**
     * 并发登录导致前一个 session 失效时的处理策略配置
     *
     * @author summer
     * @date 2019-01-17 13:06
     * @return org.springframework.security.web.session.SessionInformationExpiredStrategy
     * @version V1.0.0-RELEASE
     */
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new EasyExpiredSessionStrategy(securityProperties);
	}
	
    /**
     * 退出时的处理策略配置
     *
     * @author summer
     * @date 2019-01-17 13:07
     * @return org.springframework.security.web.authentication.logout.LogoutSuccessHandler
     * @version V1.0.0-RELEASE
     */
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new EasyLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
	}
	
}
