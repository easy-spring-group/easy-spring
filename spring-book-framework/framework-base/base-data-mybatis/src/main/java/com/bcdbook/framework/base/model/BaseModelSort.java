package com.bcdbook.framework.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 基础实体类
 *
 * @author summer
 * @date 2018-12-02 16:12
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BaseModelSort extends BaseModel {

    private static final long serialVersionUID = 8075527264641207256L;

    /**
     * 排序字段
     */
    private Integer iSort;
}
