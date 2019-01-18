package com.bcdbook.security.browser;

import com.bcdbook.security.core.authentication.FormAuthenticationConfig;
import com.bcdbook.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.bcdbook.security.core.authorize.AuthorizeConfigManager;
import com.bcdbook.security.core.properties.SecurityProperties;
import com.bcdbook.security.core.validate.code.ValidateCodeSecurityConfig;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
import java.util.Set;

/**
 * 浏览器环境下安全配置主类
 *
 * @author summer
 * @date 2019-01-17 12:10
 * @version V1.0.0-RELEASE
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入配置文件
     */
	@Autowired
	private SecurityProperties securityProperties;
    /**
     * 注入数据源
     */
	@Autowired
	private DataSource dataSource;
    /**
     * 注入用户详情的 service
     */
	@Autowired
	private UserDetailsService userDetailsService;

    /**
     * 注入短信验证码的配置类
     */
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    /**
     * 注入验证码主类的配置类
     */
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 注入 social 的配置类
     */
	@Autowired
	private SpringSocialConfigurer easySocialSecurityConfig;

    /**
     * 注入 Session 回话过期策略
     */
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    /**
     * 注入 session 失效的策略
     */
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
    /**
     * 注入登录成功的控制器
     */
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
    /**
     * 注入权限配置的管理器
     */
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
    /**
     * 注入标点验证的配置类
     */
	@Autowired
	private FormAuthenticationConfig formAuthenticationConfig;
    /**
     * 浏览器的安全回调
     * TODO 待处理
     */
	@Autowired(required = false)
	private Set<BrowserSecurityConfigCallback> configCallbacks;
	
    /**
     * web 的安全配置
     * 所有的 get 请求, 均可以忽略的静态文件信息
     *
     * @author summer
     * @date 2019-01-17 12:17
     * @param web 安全控制的 web 对象
     * @return void
     * @version V1.0.0-RELEASE
     */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
                .antMatchers(HttpMethod.GET,
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.jpg");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO 待处理
		if(CollectionUtils.isNotEmpty(configCallbacks)) {
			configCallbacks.forEach(callback -> callback.config(http));
		}
		
		formAuthenticationConfig.configure(http);
		
		http.apply(validateCodeSecurityConfig)
				.and()
                // 短信验证码的配置
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
//			.apply(easySocialSecurityConfig)
//				.and()
//			//记住我配置，如果想在'记住我'登录时记录日志，可以注册一个InteractiveAuthenticationSuccessEvent事件的监听器
//			.rememberMe()
//				.tokenRepository(persistentTokenRepository())
//				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//				.userDetailsService(userDetailsService)
//				.and()
                // session 的配置
			.sessionManagement()
				.invalidSessionStrategy(invalidSessionStrategy)
				.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
				.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
				.expiredSessionStrategy(sessionInformationExpiredStrategy)
				.and()
				.and()
			.logout()
				.logoutUrl("/signOut")
				.logoutSuccessHandler(logoutSuccessHandler)
				.deleteCookies("JSESSIONID")
				.and()
			.csrf().disable()
			.headers().frameOptions().disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
		
	}

    /**
     * 记住我功能的 token 存取器配置
     *
     * @author summer
     * @date 2019-01-17 12:20
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     * @version V1.0.0-RELEASE
     */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		/*
		 * 设置 jdbc 的数据源
		 * 注意: 设置此数据源的时候需要添加 jdbc 驱动, 否则会找不到 setDataSource 方法
		 */
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
		// 在启动的时候创建数据库
//		tokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}
	
}
