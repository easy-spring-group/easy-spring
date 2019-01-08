package com.bcdbook.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 错误返回信息枚举
 *
 * @author summer
 * @date 2019-01-07 16:01
 * @version V1.0.0-RELEASE
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorResultEnum implements CodeEnumInteger{
    /**
     * 3XX 错误, 权限错误
     */
    USER_LOGIN_ERROR(300, "登录错误", "登录失败"),
    /** 用户名或密码不正确 */
    USERNAME_OR_PASSWORD_NOT_MATCHING(301, "用户名或密码不正确", "用户名或密码不正确"),

    /** 用户名不存 */
    USER_UNKNOWN_ACCOUNT(310, "用户名不存在", "用户名不存在"),
    /** 密码不正确 */
    USER_INCORRECT_CREDENTIALS(311, "密码不正确", "密码不正确"),
    /** 验证码错误 */
    USER_KAPTCHA_VALIDATE_FAILED(312, "验证码错误", "验证码错误"),

    /** 没有权限 */
    USER_PERMISSION_DENIED(350, "没有权限", "没有权限"),
    /** cookie 已过期或被禁用 */
    USER_COOKIE_EXPIRED(351, "cookie已过期或被禁用", "cookie已过期或被禁用"),

    /**
     * 4XX 错误
     */
    PARAMETER_ERROR(410, "参数校验有误", "传入的参数和所需的参数不匹配"),
    /** 传入的必要参数为空 */
    PARAMETER_IS_EMPTY(411, "必要参数为空", "传入的参数和所需的参数不匹配"),

    /**
     * 5XX 错误
     */
    ERROR(500, "系统错误", "错误原因不明"),
    ;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示信息
     */
    private String errorMessage;
    /**
     * 错误解释
     */
    private String details;
}
