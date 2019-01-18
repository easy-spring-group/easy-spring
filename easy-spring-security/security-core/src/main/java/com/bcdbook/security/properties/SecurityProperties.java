package com.bcdbook.security.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Security 的配置主配置类
 *
 * @author summer
 * @date 2019-01-16 14:12
 * @version V1.0.0-RELEASE
 */
@ConfigurationProperties(prefix = "easy-spring.security")
@NoArgsConstructor
@Data
public class SecurityProperties {

    /**
     * 浏览器的配置类
     */
    private BrowserProperties browser = new BrowserProperties();
//    /**
//     * 验证码校验的配置类
//     */
//    private ValidateCodeProperties code = new ValidateCodeProperties();
//    /**
//     * Security Social 的配置类
//     */
//    private SocialProperties social = new SocialProperties();
//    /**
//     * OAuth2 协议的配置类
//     */
//    private OAuth2Properties oauth2 = new OAuth2Properties();
}
