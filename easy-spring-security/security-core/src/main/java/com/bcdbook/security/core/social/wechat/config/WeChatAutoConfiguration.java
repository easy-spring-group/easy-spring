package com.bcdbook.security.core.social.wechat.config;

import com.bcdbook.security.core.properties.SecurityProperties;
import com.bcdbook.security.core.properties.WeChatProperties;
import com.bcdbook.security.core.social.view.EasyConnectView;
import com.bcdbook.security.core.social.wechat.connect.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * 微信登录配置
 *
 * @author summer
 * @annotation @ConditionalOnProperty 表示当对应的配置有值的时候, 此配置项才起作用
 * @date 2019-01-16 23:32
 * @version V1.0.0-RELEASE
 */
@Configuration
@ConditionalOnProperty(prefix = "easy-spring.security.social.we-chat", name = "app-id")
public class WeChatAutoConfiguration extends SocialConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

    /**
     * TODO
     *
     * @author summer
     * @date 2019-01-16 23:35
     * @param
     * @return org.springframework.social.connect.ConnectionFactory<?>
     * @version V1.0.0-RELEASE
     */
	protected ConnectionFactory<?> createConnectionFactory() {
		WeChatProperties weChatConfig = securityProperties.getSocial().getWeChat();
		return new WeChatConnectionFactory(weChatConfig.getProviderId(), weChatConfig.getAppId(),
				weChatConfig.getAppSecret());
	}
	
	@Bean({"connect/weChatConnect", "connect/weChatConnected"})
	@ConditionalOnMissingBean(name = "weChatConnectedView")
	public View weChatConnectedView() {
		return new EasyConnectView();
	}
	
}
