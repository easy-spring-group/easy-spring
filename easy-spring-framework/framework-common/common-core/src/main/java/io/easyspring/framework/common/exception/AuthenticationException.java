package io.easyspring.framework.common.exception;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用户权限错误的异常类
 *
 * @author summer
 * DateTime 2019-01-16 14:45
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Getter
public class AuthenticationException extends CommonException {

    /**
     * 权限认证异常的构造方法
     *
     * Author summer
     * DateTime 2019-01-08 17:04
     * @param message 异常信息
     * Version V1.0.0-RELEASE
     */
    public AuthenticationException(String message){
        super(message);
        this.setCode(ErrorResultEnum.USER_NOT_LOGIN_ERROR.getCode());
    }

    /**
     * 权限认证异常的构造方法
     *
     * Author summer
     * DateTime 2019-01-16 19:02
     * @param message 错误信息
     * @param detail 错误详情
     * Version V1.0.0-RELEASE
     */
    public AuthenticationException(String message, Object detail){
        super(message);
        this.setDetails(detail);
        this.setCode(ErrorResultEnum.USER_NOT_LOGIN_ERROR.getCode());
    }
}
