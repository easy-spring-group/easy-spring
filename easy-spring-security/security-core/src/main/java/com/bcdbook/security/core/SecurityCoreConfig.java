package com.bcdbook.security.core;

import com.bcdbook.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Security 核心的配置类
 * 激活 安全配置 SecurityProperties
 *
 * @author summer
 * @date 2019-01-17 13:15
 * @version V1.0.0-RELEASE
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
