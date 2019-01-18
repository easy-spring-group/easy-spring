package com.bcdbook.security.core.validate.code.sms;

import com.bcdbook.security.core.properties.SecurityProperties;
import com.bcdbook.security.core.validate.code.ValidateCode;
import com.bcdbook.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 *
 * @author summer
 * @date 2019-01-17 14:59
 * @version V1.0.0-RELEASE
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;
	
    /**
     * 短信验证码的生成方法
     *
     * @author summer
     * @date 2019-01-17 15:00
     * @param request Servlet 请求对象
     * @return com.bcdbook.security.core.validate.code.ValidateCode
     * @version V1.0.0-RELEASE
     */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}
