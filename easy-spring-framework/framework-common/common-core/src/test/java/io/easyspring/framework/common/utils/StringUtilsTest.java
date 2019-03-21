package io.easyspring.framework.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

    /**
     * 非空检查的测试类
     *
     * Author summer
     * DateTime 2018-12-06 23:48
     * Version V1.0.0-RELEASE
     */
    @Test
    public void isEmpty(){
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty(" "));
    }

    /**
     * 驼峰形式的字符串转成下划线的测试类
     *
     * Author summer
     * DateTime 2018-12-06 23:48
     * Version V1.0.0-RELEASE
     */
    @Test
    public void camelToUnderline(){
        String str = "userName";
        String strResult = StringUtils.camelToUnderline(str);
        assertNotNull(strResult);
        assertEquals("user_name", strResult);
    }

    /**
     * 下划线命名转驼峰的测试类
     *
     * Author summer
     * DateTime 2018-12-06 23:32
     * Version V1.0.0-RELEASE
     */
    @Test
    public void underlineToCamel() {
        String sourceString = "user_name_Id_";
        String camelString = StringUtils.underlineToCamel(sourceString);
        assertNotNull(camelString);
        System.out.println("camelString: " + camelString);
        assertEquals("userNameId", camelString);
    }

    /**
     * 将字符串的首字母转成小写的测试类
     *
     * Author summer
     * DateTime 2018-12-06 23:32
     * Version V1.0.0-RELEASE
     */
    @Test
    public void toLowerCaseFirstOne() {
        String sourceString = "UserName";
        String resultString = StringUtils.toLowerCaseFirstOne(sourceString);
        assertNotNull(resultString);
        assertEquals("userName", resultString);
    }

    /**
     * 将字符串的首字母转成大写的测试类
     *
     * Author summer
     * DateTime 2018-12-06 23:32
     * Version V1.0.0-RELEASE
     */
    @Test
    public void toUpperCaseFirstOne() {
        String sourceString = "userName";
        String resultString = StringUtils.toUpperCaseFirstOne(sourceString);
        assertNotNull(resultString);
        assertEquals("UserName", resultString);
    }
    /**
     * 将 getter 或 setter 方法转换成字段名称的测试类
     *
     * Author summer
     * DateTime 2018-12-06 23:32
     * Version V1.0.0-RELEASE
     */
    @Test
    public void getFieldNameByGetterOrSetter() {
        String sourceString = "getUserName";
        String resultString = StringUtils.getFieldNameByGetterOrSetter(sourceString);
        assertNotNull(resultString);
        assertEquals("userName", resultString);
    }
}