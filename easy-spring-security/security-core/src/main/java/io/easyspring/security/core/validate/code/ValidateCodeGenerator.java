package io.easyspring.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 *
 * @author summer
 * @date 2019-01-16 23:59
 * @version V1.0.0-RELEASE
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码的接口
     *
     * @author summer
     * @date 2019-01-17 00:00
     * @param request 请求对象
     * @return io.easyspring.security.core.validate.code.ValidateCode
     * @version V1.0.0-RELEASE
     */
	ValidateCode generate(ServletWebRequest request);
	
}
