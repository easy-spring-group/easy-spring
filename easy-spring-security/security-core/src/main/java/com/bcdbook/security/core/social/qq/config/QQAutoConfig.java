package com.bcdbook.security.core.social.qq.config;

import com.bcdbook.security.core.properties.QQProperties;
import com.bcdbook.security.core.properties.SecurityProperties;
import com.bcdbook.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * autoconfigure2.04中已经不存在social的自动配置类了
 *
 * @author zhailiang
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "easy-spring.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    public ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
    }

    // 这里需要返回null，否则会返回内存的 ConnectionRepository
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }
}
