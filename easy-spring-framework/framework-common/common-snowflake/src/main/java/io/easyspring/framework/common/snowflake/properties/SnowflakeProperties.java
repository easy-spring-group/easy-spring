package io.easyspring.framework.common.snowflake.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * id 生成器的配置信息
 *
 * @author summer
 * DateTime 2018-12-02 22:45
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ConfigurationProperties(prefix = "easy-spring.framework.common.snowflake")
@Component
public class SnowflakeProperties {

    /**
     * 机器 id
     */
    private Long workerId;
    /**
     * 数据中心 id
     */
    private Long dataCenterId;
}
