package com.bcdbook.security.core.social.wechat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * WeChat API 调用模板， scope 为 Request 的Spring bean,
 * 根据当前用户的 accessToken 创建。
 *
 * @author summer
 * @date 2019-01-16 23:19
 * @version V1.0.0-RELEASE
 */
public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {

	/**
	 * Jackson 的 Mapper 对象
	 */
	private ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 获取用户信息的 url
	 */
	private static final String URL_GET_USER_INFO = "https://api.weChat.qq.com/sns/userinfo?openid=";
	
    /**
     * 根据 accessToken 的构造方法
     *
     * @author summer
     * @date 2019-01-16 23:21
     * @param accessToken accessToken
     * @version V1.0.0-RELEASE
     */
	public WeChatImpl(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
	}
	
    /**
     * 默认注册的 StringHttpMessageConverter 字符集为ISO-8859-1，
     * 而微信返回的是UTF-8的，所以覆盖了原来的方法。
     *
     * @author summer
     * @date 2019-01-16 23:23
     * @return java.util.List<org.springframework.http.converter.HttpMessageConverter<?>>
     * @version V1.0.0-RELEASE
     */
	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
	    // 获取信息转换器
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}

    /**
     * 根据 openId 获取微信用户信息
     *
     * @author summer
     * @date 2019-01-16 23:18
     * @param openId 微信的 openId
     * @return com.bcdbook.security.core.social.wechat.api.WeChatUserInfo
     * @version V1.0.0-RELEASE
     */
	@Override
	public WeChatUserInfo getUserInfo(String openId) {
	    // 请求时的地址
		String url = URL_GET_USER_INFO + openId;
		// 发送请求
		String response = getRestTemplate().getForObject(url, String.class);
		// 如果返回信息出错, 则返回空
		if(StringUtils.contains(response, ERRCODE_KEY)) {
			return null;
		}
		// 把返回的信息, 解析成微信详细信息对象
		WeChatUserInfo profile = null;
		try {
			profile = objectMapper.readValue(response, WeChatUserInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

}
