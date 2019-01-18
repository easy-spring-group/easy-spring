package com.bcdbook.security.core.validate.code;

import com.bcdbook.security.core.properties.SecurityProperties;
import com.bcdbook.security.core.validate.code.image.ImageCodeGenerator;
import com.bcdbook.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.bcdbook.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的 bean 来覆盖安全
 * 模块默认的配置。
 *
 * @author summer
 * @date 2019-01-17 15:33
 * @version V1.0.0-RELEASE
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 注入 Security 的配置
     */
	@Autowired
	private SecurityProperties securityProperties;
	
    /**
     * 图片验证码图片生成器
     *
     * @author summer
     * @date 2019-01-17 15:35
     * @return com.bcdbook.security.core.validate.code.ValidateCodeGenerator
     * @version V1.0.0-RELEASE
     */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
	    // 创建验证码生成器
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		// 设置配置文件
		codeGenerator.setSecurityProperties(securityProperties);
		// 返回生成器
		return codeGenerator;
	}
	
    /**
     * 短信验证码发送器
     *
     * @author summer
     * @date 2019-01-17 15:36
     * @return com.bcdbook.security.core.validate.code.sms.SmsCodeSender
     * @version V1.0.0-RELEASE
     */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
	    // 返回默认的短信发送器
		return new DefaultSmsCodeSender();
	}

}
