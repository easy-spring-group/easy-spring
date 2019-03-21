package io.easyspring.framework.common.utils;

import org.springframework.util.StringUtils;

/**
 * 数值的工具类
 *
 * @author summer
 * DateTime 2019-01-12 14:06
 * @version V1.0.0-RELEASE
 */
public class NumberUtils {

    /**
     * 校验传入的参数是否不是正整数
     *
     * Author summer
     * DateTime 2019-01-12 14:07
     * @param value 需要校验的 value
     * @return boolean
     * Version V1.0.0-RELEASE
     */
    public static boolean isNotPositiveIntegerOrZero(Long value){
        return value == null || value < 0;
    }

    /**
     * 校验传入的参数是否不是正整数
     *
     * Author summer
     * DateTime 2019-01-12 14:07
     * @param value 需要校验的 value
     * @return boolean
     * Version V1.0.0-RELEASE
     */
    public static boolean isNotPositiveIntegerOrZero(Integer value){
        return value == null || value < 0;
    }

    /**
     * 校验传入的参数是否不是正整数
     *
     * Author summer
     * DateTime 2019-01-12 14:07
     * @param value 需要校验的 value
     * @return boolean
     * Version V1.0.0-RELEASE
     */
    public static boolean isNotPositiveIntegerOrZero(String value){
        // 如果传入的数据为空, 则直接返回 false
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        for(int i= 0; i < value.length(); i++){
            int chr = value.charAt(i);
            if(chr < 48 || chr > 57) {
                return true;
            }
        }
        return false;
    }

}
