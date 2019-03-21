package io.easyspring.framework.common.converter;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import io.easyspring.framework.common.exception.CommonException;
import org.springframework.beans.BeanUtils;

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
        if (sourceObject == null) {
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
}
