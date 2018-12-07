package com.bcdbook.framework.utils;

import java.util.regex.Pattern;

/**
 * String 的工具类
 *
 * @author summer
 * @date 2018-12-06 22:29
 * @version V1.0.0-RELEASE
 */
public class StringUtils {

    /**
     * 定义下划线的常量字段
     */
    private static final char UNDERLINE = '_';
    /**
     * 定义空字符串的常量
     */
    private static final String EMPTY_STRING = "";
    /**
     * 定义 getter 的匹配模板
     */
    private static final Pattern PATTERN_GET = Pattern.compile("^get[A-Z].*");
    /**
     * 定义 is 的匹配模板
     */
    private static final Pattern PATTERN_IS  = Pattern.compile("^is[A-Z].*");
    /**
     * 定义 setter 的匹配模板
     */
    private static final Pattern PATTERN_SET  = Pattern.compile("^set[A-Z].*");

    /**
     * 检查字符串是否为空
     *
     * @author summer
     * @date 2018-12-06 23:23
     * @param str 需要检查的字符串
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    public static boolean isEmpty(String str){
        // 直接调用 Spring 的检查机制
        return org.springframework.util.StringUtils.isEmpty(str);
    }

    /**
     * 驼峰形式的字符串转成下划线
     *
     * @author summer
     * @date 2018-12-06 23:19
     * @param param 需要转换的字段
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    public static String camelToUnderline(String param) {
        // 参数合法性校验
        if (isEmpty(param)) {
            return param;
        }

        /*
         * 执行转换
         */
        // 获取传入字符串的长度
        int length = param.length();
        // 定义 StringBuilder 对象, 用于存储转换后的字符
        StringBuilder stringBuilder = new StringBuilder(length);
        // 循环执行转换
        for (int charIndex = 0; charIndex < length; charIndex++) {
            // 获取下标为 i 的字符
            char currentChar = param.charAt(charIndex);
            // 如果此字符是大写
            if (Character.isUpperCase(currentChar)) {
                if (charIndex == 0){
                    // 把当前字符转成小写,
                    stringBuilder.append(Character.toLowerCase(currentChar));
                } else {
                    // 在字符之前添加一个 下划线
                    stringBuilder.append(UNDERLINE);
                    // 把当前字符转成小写,
                    stringBuilder.append(Character.toLowerCase(currentChar));
                }
            } else {
                // 如果不是, 则直接加入
                stringBuilder.append(currentChar);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 下划线形式的字符串转换成驼峰
     *
     * @author summer
     * @date 2018-12-06 23:03
     * @param param 需要转换的字符串
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    public static String underlineToCamel(String param) {
        // 参数合法性校验
        if (isEmpty(param)) {
            return param;
        }
        /*
         * 执行转换
         */
        // 获取参数的长度
        int length = param.length();
        // 定义 StringBuilder 对象
        StringBuilder stringBuilder = new StringBuilder(length);
        // 循环处理每个字段
        for (int charIndex = 0; charIndex < length; charIndex++) {
            // 获取下标为 charIndex 的字符
            char currentChar = param.charAt(charIndex);
            // 如果 char 为下划线
            if (currentChar == UNDERLINE) {
                // 如果下划线不是最后一个字符, 则吧下一个字符转换成大写, 并添加到输出对象中
                if (++charIndex < length) {
                    stringBuilder.append(Character.toUpperCase(param.charAt(charIndex)));
                }
                // 如果下划线是最后一个字符, 则不处理
            } else {
                // 如果不是下划线则直接添加到 builder 中
                stringBuilder.append(currentChar);
            }
        }
        // 返回重新生成的字符串
        return stringBuilder.toString();
    }

    /**
     * 将字符串的首字母转成小写
     *
     * @author summer
     * @date 2018-12-06 23:35
     * @param str 需要转换的字符串
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    public static String toLowerCaseFirstOne(String str){
        // 参数合法性校验
        if (isEmpty(str)) {
            return str;
        }

        /*
         * 执行检查并处理
         */
        // 如果首字母是小写, 则直接返回
        if(Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            // 将首字母转成小写添加到 builder 中, 同时截取首字母之后的字符串添加到 builder 中
            // 返回转换好的字符串
            return Character.toLowerCase(str.charAt(0)) + str.substring(1);
        }
    }

    /**
     * 将字符串的首字母转成大写
     *
     * @author summer
     * @date 2018-12-06 23:35
     * @param str 需要转换的字符串
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    public static String toUpperCaseFirstOne(String str){
        // 参数合法性校验
        if (isEmpty(str)) {
            return str;
        }

        /*
         * 执行检查并处理
         */
        // 如果首字母是大写, 则直接返回
        if(Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            // 如果首字母不是大写, 则转换成大写, 再拼装后面的字符串然后返回
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
    }

    /**
     * 将 getter 或 setter 方法转换成字段名称
     *
     * @author summer
     * @date 2018-12-07 00:15
     * @param methodName 需要转换的方法名
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    public static String getFieldNameByGetterOrSetter(String methodName) {
        // 参数合法性校验
        if (isEmpty(methodName)) {
            return methodName;
        }

        // 如果是 getter 方法或 setter 方法
        if (PATTERN_GET.matcher(methodName).matches() || PATTERN_SET.matcher(methodName).matches()) {
            // 截取前三个字符
            methodName = methodName.substring(3);
        } else if (PATTERN_IS.matcher(methodName).matches()) {
            // 如果是 is 方法, 截取前两个字符
            methodName = methodName.substring(2);
        }

        // 将首字母转成小写并返回
        return toLowerCaseFirstOne(methodName);
    }
}
