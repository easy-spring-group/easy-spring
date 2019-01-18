/**
 * 
 */
package com.bcdbook.security.core.authentication;

import com.bcdbook.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 表单登录配置
 * 
 * @author zhailiang
 */
@Component
public class FormAuthenticationConfig {

	@Autowired
	protected AuthenticationSuccessHandler easyAuthenticationSuccessHandler;
	
	@Autowired
	protected AuthenticationFailureHandler easyAuthenticationFailureHandler;
	
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(SecurityConstants.AUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.SIGN_IN_PROCESSING_URL_FORM)
			.successHandler(easyAuthenticationSuccessHandler)
			.failureHandler(easyAuthenticationFailureHandler);
	}
	
}
