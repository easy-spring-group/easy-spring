package io.easyspring.framework.common.converter;

import io.easyspring.framework.common.exception.CommonException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象转 map 的转换器
 *
 * @author summer
 * @version V1.0.0-RELEASE
 * DateTime 2019-03-30 19:23
 */
public class Object2MapConverter {

    /**
     * 转换对象为 Map
     *
     * @param obj 需要转换的对象
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-03-30 19:24
     */
    public static Map<String, Object> convert(Object obj) {
        // 数据检查
        if (obj == null) {
            return null;
        }

        // 创建新的 map 对象
        Map<String, Object> map = new HashMap<>(16);

        // 获取出入的对象的类
        Class clazz = obj.getClass();
        // 获取字段集合
        Field[] fields = clazz.getDeclaredFields();
        try {
            // 循环处理字段
            for (Field field : fields) {
                // 设置其可操作性
                field.setAccessible(true);
                // 对象中设置值
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("对象转 map 出现异常");
        }

        // 返回封装好的数据
        return map;
    }

    /**
     * 反向转换的方法
     *
     * @param map 需要转换的 map 对象
     * @param clazz 需要转换成的类型
     * @return java.lang.Object
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-03-30 19:31
     */
    public static <T> T reverse(Map<String, Object> map, Class<T> clazz) {
        // 执行参数校验
        if (map == null || clazz == null) {
            return null;
        }

        // 定义返回数据类型
        T resultObject = null;
        try {
            // 生成返回的数据对象
            resultObject = clazz.newInstance();

            // 获取返回对象的参数
            Field[] fields = resultObject.getClass().getDeclaredFields();
            // 循环参数进行处理
            for (Field field : fields) {
                // 获取参数的修改器
                int mod = field.getModifiers();
                // 如果是 static 类型或 final 类型的, 直接跳过
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }

                // 设置参数的可操作性
                field.setAccessible(true);
                // 执行值的射入
                field.set(resultObject, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("Map 转对象时出现转换错误");
        }

        // 返回转换后的结果
        return resultObject;
    }
}
