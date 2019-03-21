package io.easyspring.framework.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Java Bean 的工具类
 *
 * @author summer
 * DateTime 2018-12-07 21:27
 * @version V1.0.0-RELEASE
 */
public class BeanUtils {

    /**
     * 定义 setter 方法的前缀
     */
    private static final String SET_PREFIX = "set";

    /**
     * map 对象转成 bean 的方法
     *
     * Author summer
     * DateTime 2018-12-07 21:44
     * @param map 想要转换的 map 对象
     * @param clazz 想要转换成的类型
     * @return T
     * Version V1.0.0-RELEASE
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {
        // 参数合法性校验
        if (CollectionUtils.isEmpty(map) || clazz == null) {
            return null;
        }

        // 构建对象
        T bean = clazz.newInstance();
        // 获取所有方法
        Method[] methods = clazz.getMethods();
        // 循环处理所有的方法
        for (Method method : methods) {
            // 如果是 setter 方法
            if (method.getName().startsWith(SET_PREFIX)) {
                // 截取属性名
                String fieldName = StringUtils.getFieldNameByGetterOrSetter(method.getName());
                // 获取属性名下划线的方式
                String columnName = StringUtils.camelToUnderline(fieldName);

                // 如果 map 中直接包含以属性名为 key 的字段
                if (map.containsKey(fieldName)) {
                    // 设置值到 bean 中
                    method.invoke(bean, map.get(fieldName));
                } else if (map.containsKey(columnName)) {
                    // 如果 map 中的 key 是以下划线的方式命名的
                    method.invoke(bean, map.get(columnName));
                }
            }
        }
        // 返回转换后的对象
        return bean;
    }

    /**
     * map 对象转成 bean 的方法
     *
     * Author summer
     * DateTime 2018-12-07 23:25
     * @param map 想要转换的 map 对象
     * @param clazz 想要转换成的类型
     * @param ignoreDataType 转换时是否忽略数据类型
     * @return T
     * Version V1.0.0-RELEASE
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz, boolean ignoreDataType)
            throws IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        // 如果不忽略数据类型
        if (!ignoreDataType){
            return mapToBean(map, clazz);
        }

        // 参数合法性校验
        if (CollectionUtils.isEmpty(map) || clazz == null) {
            return null;
        }

        // 定义新的 map 对象
        Map<String, Object> newMap = new HashMap<>(map.size());
        // 循环处理老的 map 集合
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            // 对 map 中的 key 执行转换, 转换成驼峰命名
            String newKey = StringUtils.underlineToCamel(entry.getKey());
            // 封装新的 map
            newMap.put(newKey, entry.getValue());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(newMap);
        return objectMapper.readValue(jsonString, clazz);
    }


    /**
     * Bean 转 map 的方法
     *
     * Author summer
     * DateTime 2018-12-07 22:30
     * @param object 想要转换的 map
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * Version V1.0.0-RELEASE
     */
    public static Map<String, Object> beanToMap(Object object) throws IllegalAccessException {
        // 参数合法性校验
        if(object == null){
            return null;
        }

        // 定义属性的 list 对象
        List<Field> fieldList = new ArrayList<>();
        // 获取传入对象的类
        Class tempClass = object.getClass();
        while (tempClass != null) {
            // 获取当前类的所有属性, 并添加到 fieldList 中
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            // 递归获取父级的类型
            tempClass = tempClass.getSuperclass();
        }

        // 定义输出的对象
        Map<String, Object> map = new HashMap<>(16);
        // 循环处理每一个字段
        for (Field field : fieldList) {
            field.setAccessible(true);
            // 设置内容到 map 中
            map.put(field.getName(), field.get(object));
        }

        // 返回封装好的数据
        return map;
    }
}
