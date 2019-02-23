package io.easyspring.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码的异常类
 *
 * @author summer
 * @date 2019-01-17 00:03
 * @version V1.0.0-RELEASE
 */
public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = -7285211528095468156L;

    /**
     * 验证码异常的构造器
     *
     * @author summer
     * @date 2019-01-17 00:04
     * @param errorMessage 错误信息
     * @version V1.0.0-RELEASE
     */
	public ValidateCodeException(String errorMessage) {
		super(errorMessage);
	}

}
