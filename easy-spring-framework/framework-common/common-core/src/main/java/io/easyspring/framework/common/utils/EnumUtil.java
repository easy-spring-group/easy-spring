package io.easyspring.framework.common.utils;


import io.easyspring.framework.common.enums.CodeEnumInteger;
import io.easyspring.framework.common.enums.CodeEnumString;

/**
 * 枚举的工具类
 *
 * @author summer
 * DateTime 2019-01-08 14:45
 * @version V1.0.0-RELEASE
 */
public class EnumUtil {

    /**
     * 根据 Integer 类型的 code 值,获取相应的枚举对象
     *
     * Author summer
     * DateTime 2019-01-08 14:46
     * @param code 代表值
     * @param enumClass 枚举的 class 类型
     * @return T
     * Version V1.0.0-RELEASE
     */
    public static <T extends CodeEnumInteger> T getByCode(Integer code, Class<T> enumClass) {
        // 循环获取对应的枚举值, 如果匹配则返回
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        // 如果没有匹配的枚举对象, 则返回null
        return null;
    }

    /**
     * 根据 String 类型的 code 值,获取相应的枚举对象
     *
     * Author summer
     * DateTime 2019-01-08 14:47
     * @param code key 值
     * @param enumClass 枚举的 class 类型
     * @return T
     * Version V1.0.0-RELEASE
     */
    public static <T extends CodeEnumString> T getByCode(String code, Class<T> enumClass) {
        // 循环获取对应的枚举值, 如果匹配则返回
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        // 如果没有匹配的枚举对象, 则返回null
        return null;
    }
}
