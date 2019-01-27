package com.bcdbook.framework.base.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 执行排序的 form 表单
 *
 * @author summer
 * @date 2018-12-09 15:03
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class BaseSortForm implements Serializable {

    private static final long serialVersionUID = -3665784508459557185L;

    /**
     * 表 id
     */
    @NotNull(message = "要排序的对象 id 不能为空")
    @Min(value = 0, message = "需要排序的 id 不能小于 0")
    private Long id;

    /**
     * 新的排序值
     */
    @NotNull(message = "排序值不能为空")
    @Min(value = 0, message = "排序值不能小于 0")
    private Integer easySort;
}
