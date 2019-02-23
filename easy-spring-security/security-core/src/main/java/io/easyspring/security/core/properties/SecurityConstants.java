package io.easyspring.security.core.properties;

/**
 * Security 配置的常量类
 *
 * @author summer
 * @date 2019-01-16 20:43
 * @version V1.0.0-RELEASE
 */
@SuppressWarnings("unused")
public interface SecurityConstants {
	/**
	 * 默认的处理验证码的 url 前缀
	 */
	String VALIDATE_CODE_URL_PREFIX = "/validate/code";
	/**
	 * 当请求需要身份认证时，默认跳转的url
	 */
	String AUTHENTICATION_URL = "/authentication/require";

	/**
	 * 默认的用户名密码登录请求处理 url
	 */
	String SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
	/**
	 * 默认的手机验证码登录请求处理 url
	 */
	String SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";
	/**
	 * 默认的 OPENID 登录请求处理url
	 */
	String SIGN_IN_PROCESSING_URL_OPENID = "/authentication/openid";

	/**
	 * 默认登录页面
	 */
	String SIGN_IN_PAGE_URL = "/signIn.html";
    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
	String SOCIAL_SIGN_UP_URL = "/signUp.html";
	/**
	 * session失效默认的跳转地址
	 */
	String SESSION_INVALID_URL = "/session-invalid.html";

	/**
	 * 验证图片验证码时，http 请求中默认的携带图片验证码信息的参数的名称
	 */
	String PARAMETER_NAME_CODE_IMAGE = "imageCode";
	/**
	 * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
	 */
	String PARAMETER_NAME_CODE_SMS = "smsCode";
	/**
	 * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
	 */
	String PARAMETER_NAME_MOBILE = "mobile";

	/**
	 * openid参数名
	 */
	String PARAMETER_NAME_OPENID = "openId";
	/**
	 * providerId 参数名(第三方登录提供商参数名)
	 */
	String PARAMETER_NAME_PROVIDER_ID = "providerId";
	/**
	 * 获取第三方用户信息的url
	 */
	String SOCIAL_USER_INFO_URL = "/social/user";
    /**
     * 社交登录功能拦截的 url
     */
	String SOCIAL_FILTER_PROCESSES_URL = "/auth";

    /**
     * OAuth2 的 Token 加密时使用的 key
     */
    String JWT_SIGNING_KEY = "easy-spring";
    /**
     * social 关联登录的表前缀
     */
    String SOCIAL_CONNECTION_TABLE_PREFIX = "easy_";
}
