package com.bcdbook.security;

import com.bcdbook.security.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 让SecurityProperties这个配置类生效
 * EnableConfigurationProperties 的作用是标明加载哪一个类
 * 这效果和直接在目标类上写上 @Configuration 效果一样
 *
 * @author summer
 * @date 2019-01-16 14:16
 * @version V1.0.0-RELEASE
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class CoreSecurityConfig {

    /**
     * 定义加密器
     * TODO 此处需要允许用户配置, 如果用户不配置, 当前的加密解密器
     *
     * @author summer
     * @date 2019-01-16 13:35
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @version V1.0.0-RELEASE
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
