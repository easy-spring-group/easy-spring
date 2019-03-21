package io.easyspring.framework.common.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通用的异常的配置对象
 *
 * @author summer
 * DateTime 2018-12-03 20:02
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "easy-spring.framework.common.exception")
@Component
public class CommonExceptionProperties {

    /**
     * 错误页面
     */
    private String errorPage = "500.html";
    /**
     * 错误信息的名称
     */
    private String exceptionName = "exception";
}
