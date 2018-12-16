package com.bcdbook.framework.base.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class BaseSortFrom implements Serializable {

    private static final long serialVersionUID = -3665784508459557185L;

    /**
     * 表 id
     */
    private Long id;
    /**
     * 新的排序值
     */
    private Integer iSort;
}
