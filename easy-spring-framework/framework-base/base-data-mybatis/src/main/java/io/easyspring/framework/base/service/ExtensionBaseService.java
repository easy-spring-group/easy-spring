package io.easyspring.framework.base.service;

import io.easyspring.framework.base.form.BaseSortForm;

import java.util.List;

/**
 * 扩展的通用的 service 类
 *
 * @author summer
 * @version V1.0.0-RELEASE
 * DateTime 2018-12-05 23:15
 */
public interface ExtensionBaseService<T> extends BaseService<T> {

    /**
     * 根据传入的排序对象, 获取排序结构的字符串
     *
     * Author summer
     * DateTime 2018/8/21 上午2:04
     * @param baseSortFrom 排序时提交的表单
     * @param clazz 排序时对应的实体的类型
     * @throws IllegalAccessException 反射异常
     * @throws InstantiationException 实例化异常
     * @return java.lang.String
     * Version V1.0.0-RELEASE
     */
    int sort(BaseSortForm baseSortFrom, Class<T> clazz) throws IllegalAccessException, InstantiationException;

    /**
     * 根据传入的排序对象集合, 获取排序结构的字符串
     *
     * Author summer
     * DateTime 2018/8/21 上午2:04
     * @param baseSortFromList 排序时提交的表单集合
     * @param clazz 排序时对应的实体的类型
     * @throws IllegalAccessException 反射异常
     * @throws InstantiationException 实例化异常
     * @return java.lang.String
     * Version V1.0.0-RELEASE
     */
    int sort(List<BaseSortForm> baseSortFromList, Class<T> clazz) throws IllegalAccessException, InstantiationException;
}