package io.easyspring.framework.common.converter;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import io.easyspring.framework.common.exception.CommonException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 通用的对象转换器
 *
 * @author summer
 * DateTime 2019-01-11 17:33
 * @version V1.0.0-RELEASE
 */
public class CommonConverter {

    /**
     * 通用的转换器
     *
     * Author summer
     * DateTime 2019-01-11 17:29
     * @param sourceObject 原数据
     * @param clazz 想要转换成的数据的 class
     * @return T
     * Version V1.0.0-RELEASE
     */
    public static <T> T simpleConvert(Object sourceObject, Class<T> clazz) {
        // 参数校验
        if (sourceObject == null || clazz == null) {
            return null;
        }

        // 创建转换后的对象实例
        T entity = null;
        try {
            entity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new CommonException(ErrorResultEnum.ERROR, e.getMessage());
        }

        // 执行属性拷贝
        BeanUtils.copyProperties(sourceObject, entity);

        // 返回转换好的数据
        return entity;
    }

    /**
     * 简单的集合转换
     *
     * @param sourceObjectList 源数据集合
     * @param clazz 需要转换成的类
     * @return java.util.List<T>
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-03-30 20:29
     */
    public static <T> List<T> simpleConvert(List<Object> sourceObjectList, Class<T> clazz) {
        // 参数校验
        if (CollectionUtils.isEmpty(sourceObjectList) || clazz == null) {
            return null;
        }

        // 执行循环转换
        return sourceObjectList.stream()
                // 过滤掉空值
                .filter(Objects::nonNull)
                // 执行循环转换
                .map(sourceObject -> {
                    return simpleConvert(sourceObject, clazz);
                }).collect(Collectors.toList());
    }
}
