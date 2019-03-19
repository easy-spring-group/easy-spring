package io.easyspring.service.file.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 阿里云文件存储的配置类
 *
 * @author summer
 * @date 2019-02-12 16:37
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class OssProperties {

    /**
     * 最长缓存时间 (默认 18 小时)
     */
    private Long maxExpiration = FileConstants.Oss.MAX_EXPIRATION;
    /**
     * 默认缓存时长 (默认 2 小时)
     */
    private Long defaultExpiration = FileConstants.Oss.DEFAULT_EXPIRATION;
    /**
     * 较短的缓存时长 (默认 10 分钟)
     */
    private Long shortExpiration = FileConstants.Oss.SHORT_EXPIRATION;

    /**
     * 连接的服务器(对应的阿里服务域名)
     */
    private String endpoint = "";
    /**
     * 识别码 id
     */
    private String accessKeyId = "";
    /**
     * 识别码对应的秘钥
     */
    private String accessKeySecret = "";
}
