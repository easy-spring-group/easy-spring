package io.easyspring.framework.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @author summer
 * DateTime 2019-01-12 00:33
 * @version V1.0.0-RELEASE
 */
public class CollectionUtils {


    /**
     * 根据传入的源数据集合和函数, 获取函数对应的值的 List 集合
     *
     * Author summer
     * DateTime 2019-01-12 00:34
     * @param sourceCollection 原数据集合
     * @param function 要执行的函数
     * @return java.util.List<java.lang.Long>
     * Version V1.0.0-RELEASE
     */
    public static  <T, R> List<R> buildList(Collection<T> sourceCollection, Function<T, R> function){
        // 传入参数校验
        if (org.springframework.util.CollectionUtils.isEmpty(sourceCollection)) {
            return null;
        }

        // 循环集合, 封装新的数据
        return sourceCollection.stream()
                .filter(Objects::nonNull)
                .map(function)
                .collect(Collectors.toList());

    }
}
