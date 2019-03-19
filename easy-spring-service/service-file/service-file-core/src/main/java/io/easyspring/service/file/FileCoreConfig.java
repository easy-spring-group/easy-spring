package io.easyspring.service.file;

import io.easyspring.service.file.properties.FileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件管理的核心的配置文件
 *
 * 注解 @Configuration 表明这是一个配置类
 * 注解 @EnableConfigurationProperties 此配置文件激活的配置类
 *
 * @author summer
 * @date 2019-02-12 16:34
 * @version V1.0.0-RELEASE
 */
@Configuration
@EnableConfigurationProperties(FileProperties.class)
public class FileCoreConfig {
}
