package io.easyspring.security.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础的社交账户配置文件
 *
 * @author summer
 * @date 2019-01-16 23:06
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class EasySocialProperties {
    /**
     * APP 的识别码
     */
    private String appId;
    /**
     * APP 的密码
     */
    private String appSecret;
}
