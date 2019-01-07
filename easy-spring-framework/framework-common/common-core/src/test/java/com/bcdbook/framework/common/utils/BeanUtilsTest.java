package com.bcdbook.framework.common.utils;

import com.bcdbook.framework.common.utils.model.User;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * map 和 bean 之间互相转换的测试类
 *
 * @author summer
 * @date 2018-12-07 21:45
 * @version V1.0.0-RELEASE
 */
public class BeanUtilsTest {

    /**
     * map 对象转成 bean 的方法的测试方法
     *
     * @author summer
     * @date 2018-12-07 21:46
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void mapToBean() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        String username = "summer";
        Integer age = 200;
        Date date = new Date();
        Date updateTime = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", username);
        map.put("age", age);
        map.put("birthday", date);
        map.put("update_time", updateTime);


        User user = BeanUtils.mapToBean(map, User.class);
        assertNotNull(user);
        assertEquals(username, user.getUserName());
        assertEquals(age, user.getAge());
        assertEquals(date, user.getBirthday());
        assertEquals(updateTime, user.getUpdateTime());
    }

    /**
     * 测试 bean 转 map 的方法
     *
     * @author summer
     * @date 2018-12-07 22:10
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void beanToMap() throws IllegalAccessException {
        String username = "summer";
        Integer age = 200;
        Date date = new Date();
        Date updateTime = new Date();

        // 创建对象
        User user = new User();
        user.setUserName(username);
        user.setAge(age);
        user.setBirthday(date);
        user.setUpdateTime(updateTime);

        Map<String, Object> map = BeanUtils.beanToMap(user);
        assertNotNull(map);
        assertEquals(username, map.get("userName"));
        assertEquals(age, map.get("age"));
        assertEquals(updateTime, map.get("updateTime"));
    }

    /**
     * map 对象转成 bean 的方法(使用 Jackson 的方式)
     *
     * @author summer
     * @date 2018-12-07 23:51
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void mapToBeanIgnoreDataType()
            throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException {
        String username = "summer";
        Integer age = 200;
        Date date = new Date();
        Date updateTime = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", username);
        map.put("age", age);
        map.put("birthday", date);
        map.put("update_time", updateTime);


        User user = BeanUtils.mapToBean(map, User.class, true);
        assertNotNull(user);
        assertEquals(username, user.getUserName());
        assertEquals(age, user.getAge());
        assertEquals(date, user.getBirthday());
        assertEquals(updateTime, user.getUpdateTime());
    }
}