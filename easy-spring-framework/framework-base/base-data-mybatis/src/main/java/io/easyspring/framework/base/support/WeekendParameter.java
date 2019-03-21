package io.easyspring.framework.base.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.weekend.Fn;

/**
 * 构建 weekend 查询的时候使用的参数数据
 *
 * @author summer
 * DateTime 2019-03-16 16:35
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeekendParameter<T> {

    /**
     * 对应字段的 function
     */
    Fn<T, Object> function;
    /**
     * 字段对应的数据
     */
    Object value;

    /**
     * 查询参数的构建器
     *
     * @param function 查询参数对应的 function
     * @param value 查询的值
     * @return io.easyspring.framework.base.support.WeekendParameter<A>
     * Author summer
     * DateTime 2019-03-16 17:09
     * Version V1.0.0-RELEASE
     */
    public static <A> WeekendParameter<A> of(Fn<A, Object> function, Object value) {
        return new WeekendParameter<>(function, value);
    }
}
