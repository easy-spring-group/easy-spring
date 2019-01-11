package com.bcdbook.framework.common.validator;

/**
 * 用户定义正则的常量类
 *
 * @author summer
 * @date 2019-01-11 12:29
 * @version V1.0.0-RELEASE
 */
public interface RegexConstant {

    /**
     * 手机号
     */
    String TELEPHONE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
}
