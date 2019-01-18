/**
 * 
 */
package com.bcdbook.security.core.social.wechat.connect;

import com.bcdbook.security.core.social.wechat.api.WeChat;
import com.bcdbook.security.core.social.wechat.api.WeChatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 
 * 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 * 
 * @author zhailiang
 *
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {
	
	/**
	 * 微信获取授权码的url
	 */
	private static final String URL_AUTHORIZE = "https://open.weChat.qq.com/connect/qrconnect";
	/**
	 * 微信获取accessToken的url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weChat.qq.com/sns/oauth2/access_token";

	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeChatServiceProvider(String appId, String appSecret) {
		super(new WeChatOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
	}


	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public WeChat getApi(String accessToken) {
		return new WeChatImpl(accessToken);
	}

}
