package io.easyspring.security.core.validate.code;

import io.easyspring.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成校验码的请求处理器
 *
 * @author summer
 * @date 2019-01-17 15:07
 * @version V1.0.0-RELEASE
 */
@RestController
public class ValidateCodeController {
    /**
     * 注入验证码处理器的管理器
     */
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor} 接口实现
     *
     * @author summer
     * @date 2019-01-17 13:38
     * @param request 请求对象
     * @param response 返回对象
     * @param type 验证码的类型(根据不同的验证码类型, 创建不同的验证码处理器)
     * @return void
     * @version V1.0.0-RELEASE
     */
	@GetMapping(SecurityConstants.VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {

		validateCodeProcessorHolder
                .findValidateCodeProcessor(type)
                .create(new ServletWebRequest(request, response));
	}

}
