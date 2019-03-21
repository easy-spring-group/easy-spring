package io.easyspring.framework.common.validator;

/**
 * 用户定义正则的常量类
 *
 * @author summer
 * DateTime 2019-01-11 12:29
 * @version V1.0.0-RELEASE
 */
public interface RegexConstant {

    /**
     * 手机号
     */
    String TELEPHONE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
    /**
     * 18 位身份证号码
     */
    String ID_CARD_18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    /**
     * 15 位身份证号码
     */
    String ID_CARD_15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$";
}
