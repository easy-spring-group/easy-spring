/**
 * 
 */
package com.bcdbook.security.core.social.wechat.connect;

import com.bcdbook.security.core.social.wechat.api.WeChat;
import com.bcdbook.security.core.social.wechat.api.WeChatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * 
 * 
 * @author zhailiang
 *
 */
public class WeChatAdapter implements ApiAdapter<WeChat> {
	
	private String openId;
	
	public WeChatAdapter() {}
	
	public WeChatAdapter(String openId){
		this.openId = openId;
	}

	/**
	 * @param api
	 * @return
	 */
	@Override
	public boolean test(WeChat api) {
		return true;
	}

	/**
	 * @param api
	 * @param values
	 */
	@Override
	public void setConnectionValues(WeChat api, ConnectionValues values) {
		WeChatUserInfo profile = api.getUserInfo(openId);
		values.setProviderUserId(profile.getOpenid());
		values.setDisplayName(profile.getNickname());
		values.setImageUrl(profile.getHeadimgurl());
	}

	/**
	 * @param api
	 * @return
	 */
	@Override
	public UserProfile fetchUserProfile(WeChat api) {
		return null;
	}

	/**
	 * @param api
	 * @param message
	 */
	@Override
	public void updateStatus(WeChat api, String message) {
		//do nothing
	}

}
