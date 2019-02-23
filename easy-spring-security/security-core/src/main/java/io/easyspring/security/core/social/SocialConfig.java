package io.easyspring.security.core.social;

import io.easyspring.security.core.properties.SecurityConstants;
import io.easyspring.security.core.properties.SecurityProperties;
import io.easyspring.security.core.social.support.EasySpringSocialConfigurer;
import io.easyspring.security.core.social.support.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 社交登录配置主类
 *
 * @author summer
 * @date 2019-01-17 18:55
 * @version V1.0.0-RELEASE
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    /**
     * 注入数据源
     */
	@Autowired
	private DataSource dataSource;
    /**
     * 注入 Security 的配置信息
     */
	@Autowired
	private SecurityProperties securityProperties;

    /**
     * 注入社交账户的登录器
     */
	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

    /**
     * 注入 social 权限过滤器的处理器
     */
	@Autowired(required = false)
	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    /**
     * 获取关联登录的数据操作的方法
     *
     * @author summer
     * @date 2019-01-18 13:17
     * @param connectionFactoryLocator 关联登录工厂的定位器
     * @return org.springframework.social.connect.UsersConnectionRepository
     * @version V1.0.0-RELEASE
     */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
	    // 创建数据库操作的类
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		// 设置数据库前缀
		repository.setTablePrefix(SecurityConstants.SOCIAL_CONNECTION_TABLE_PREFIX);
		// 如果社交账户的登录器不为空, 则设置登录器
		if(connectionSignUp != null) {
			repository.setConnectionSignUp(connectionSignUp);
		}
		// 返回数据操作对象
		return repository;
	}

    /**
     * 社交登录配置类，供浏览器或app模块引入设计登录配置用。
     *
     * @author summer
     * @date 2019-01-18 13:23
     * @return org.springframework.social.security.SpringSocialConfigurer
     * @version V1.0.0-RELEASE
     */
	@Bean
	public SpringSocialConfigurer easySocialSecurityConfig() {
	    // 获取社交登录拦截的 url
		String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        // 创建自己的 social 配置
		EasySpringSocialConfigurer configurer = new EasySpringSocialConfigurer(filterProcessesUrl);
		// 设置登录地址
		configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		// 设置过滤器处理器
		configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
		// 返回封装好的配置类对象
		return configurer;
	}

    /**
     * 用来处理注册流程的工具类
     *
     * @author summer
     * @date 2019-01-18 13:34
     * @param connectionFactoryLocator 关联工厂的加载器
     * @return org.springframework.social.connect.web.ProviderSignInUtils
     * @version V1.0.0-RELEASE
     */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,
				getUsersConnectionRepository(connectionFactoryLocator)) {
		};
	}

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    //https://docs.spring.io/spring-social/docs/1.1.x-SNAPSHOT/reference/htmlsingle/#creating-connections-with-connectcontroller
    // 必须要添加一个处理器
    // 后补：这个是提供查询社交账户信息服务，绑定服务，等
    @Bean
    public ConnectController connectController(
            ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
