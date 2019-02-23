package io.easyspring.framework.common.utils;

import org.springframework.util.StringUtils;

/**
 * weekend 查询条件的工具类
 *
 * @author summer
 * @version V1.0.0-RELEASE
 * @date 2018/10/3 下午3:57
 */
public class WeekendCriteriaUtils {

    /**
     * like 查询, 参数需要添加的前缀
     */
    private static final String LIKE_PARAMETER_PREFIX = "%";
    /**
     * like 查询, 参数需要添加的后
     */
    private static final String LIKE_PARAMETER_SUFFIX = "%";

    /**
     * like 语句中, 查询需要添加前后 %%, 此方法用于格式化对应的字段参数
     *
     * @param parameter 需要格式化的参数
     * @return java.lang.String
     * @author summer
     * @date 2018/10/3 下午3:59
     * @version V1.0.0-RELEASE
     */
    public static String buildLikeParameter(String parameter) {
        if (StringUtils.isEmpty(parameter)) {
            parameter = "";
        }

        return LIKE_PARAMETER_PREFIX + parameter + LIKE_PARAMETER_SUFFIX;
    }
}
