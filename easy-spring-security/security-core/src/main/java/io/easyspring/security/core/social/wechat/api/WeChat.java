package io.easyspring.security.core.social.wechat.api;

/**
 * 微信 API 调用接口
 *
 * @author summer
 * @date 2019-01-16 23:17
 * @version V1.0.0-RELEASE
 */
public interface WeChat {

    /**
     * 微信请求错误时返回的错误标识
     */
    String ERRCODE_KEY = "errcode";

    /**
     * 根据 openId 获取微信用户信息
     *
     * @author summer
     * @date 2019-01-16 23:18
     * @param openId 微信的 openId
     * @return io.easyspring.security.core.social.wechat.api.WeChatUserInfo
     * @version V1.0.0-RELEASE
     */
	WeChatUserInfo getUserInfo(String openId);
	
}
