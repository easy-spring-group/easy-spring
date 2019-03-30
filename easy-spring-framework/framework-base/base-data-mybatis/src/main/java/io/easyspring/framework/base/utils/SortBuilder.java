package io.easyspring.framework.base.utils;

import org.springframework.data.domain.Sort;

/**
 * 排序信息的工具类
 *
 * @author summer
 * @version V1.0.0-RELEASE
 * DateTime 2019-03-30 20:14
 */
public class SortBuilder {

    /**
     * 把传入的排序对象转换成排序查询的字符串
     *
     * @param sort 排序对象
     * @return java.lang.String
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-03-30 20:15
     */
    public static String build(Sort sort){
        // 参数合法性校验
        if(sort == null || sort.isUnsorted()){
            return null;
        }

        // 定义用于返回的数据对象
        StringBuilder orderBy = new StringBuilder(" ");
        int count = 0;
        // 拼接排序条件
        for(Sort.Order order : sort){
            String property = order.getProperty();
            String direction = order.getDirection().name();
            if(count > 0){
                orderBy.append(", ");
            }
            orderBy.append(property);
            orderBy.append(" ");
            orderBy.append(direction);

            count++;
        }

        // 返回封装好的数据
        return orderBy.toString();
    }
}
