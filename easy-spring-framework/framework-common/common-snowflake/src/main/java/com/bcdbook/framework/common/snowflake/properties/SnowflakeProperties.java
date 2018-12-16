package com.bcdbook.framework.common.snowflake.properties;

import com.bcdbook.framework.common.snowflake.constant.SnowflakePropertiesKeyConstant;
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
 * @date 2018-12-02 22:45
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ConfigurationProperties(prefix = SnowflakePropertiesKeyConstant.SNOWFLAKE_PROPERTIES_PREFIX)
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
