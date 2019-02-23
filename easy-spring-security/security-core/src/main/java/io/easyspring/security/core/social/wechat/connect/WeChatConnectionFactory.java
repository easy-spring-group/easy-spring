package io.easyspring.security.core.social.wechat.connect;

import io.easyspring.security.core.social.wechat.api.WeChat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * 微信连接工厂
 * 
 * @author zhailiang
 *
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {
	
	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeChatConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new WeChatServiceProvider(appId, appSecret), new WeChatAdapter());
	}
	
	/**
	 * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
	 */
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		if(accessGrant instanceof WeChatAccessGrant) {
			return ((WeChatAccessGrant)accessGrant).getOpenId();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.oauth2.AccessGrant)
	 */
	public Connection<WeChat> createConnection(AccessGrant accessGrant) {
		return new OAuth2Connection<WeChat>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
				accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
	 */
	public Connection<WeChat> createConnection(ConnectionData data) {
		return new OAuth2Connection<WeChat>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
	}
	
	private ApiAdapter<WeChat> getApiAdapter(String providerUserId) {
		return new WeChatAdapter(providerUserId);
	}
	
	private OAuth2ServiceProvider<WeChat> getOAuth2ServiceProvider() {
		return (OAuth2ServiceProvider<WeChat>) getServiceProvider();
	}

	
}
