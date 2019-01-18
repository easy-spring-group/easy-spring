package com.bcdbook.security.core.social;

import com.bcdbook.security.core.social.support.SocialUserInfo;
import org.springframework.social.connect.Connection;

/**
 * Social 的接口抽象类
 *
 * @author summer
 * @date 2019-01-17 18:10
 * @version V1.0.0-RELEASE
 */
public abstract class SocialController {

    /**
     * 根据 Connection 信息构建 SocialUserInfo
     *
     * @author summer
     * @date 2019-01-17 18:07
     * @param connection spring social 的连接信息
     * @return com.bcdbook.security.core.social.support.SocialUserInfo
     * @version V1.0.0-RELEASE
     */
	protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
	    // 创建简单的 social 用户信息, 用于封装返回信息
		SocialUserInfo userInfo = new SocialUserInfo();
		// 设置提供者的 id
		userInfo.setProviderId(connection.getKey().getProviderId());
		// 设置提供者的用户 id
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		// 设置昵称
		userInfo.setNickname(connection.getDisplayName());
		// 设置头像
		userInfo.setHeadImg(connection.getImageUrl());

		// 返回封装好的信息
		return userInfo;
	}
	
}
