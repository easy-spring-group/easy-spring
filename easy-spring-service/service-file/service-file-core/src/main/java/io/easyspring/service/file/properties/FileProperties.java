package io.easyspring.service.file.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件的核心配置类
 *
 * @author summer
 * @date 2019-02-12 16:36
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "easy-spring.file")
public class FileProperties {

    /**
     * 文件存储区的名称的 map 配置
     */
    private Map<String, String> bucketName = new HashMap<>();
    /**
     * 文件名称前缀的 map 配置
     */
    private Map<String, String> objectNamePrefix = new HashMap<>();

    /**
     * 阿里云文件存储的配置类
     */
    private OssProperties oss = new OssProperties();
}
