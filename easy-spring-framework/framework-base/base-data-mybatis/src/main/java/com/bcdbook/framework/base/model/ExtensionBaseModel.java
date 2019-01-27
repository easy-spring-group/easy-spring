package com.bcdbook.framework.base.model;

import com.bcdbook.framework.common.view.CommonJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 扩展后的基础实体类
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
public class ExtensionBaseModel extends BaseModel {

    private static final long serialVersionUID = 8075527264641207256L;

    /**
     * 定义最小的排序值
     */
    public static final int MIN_SORT_SIZE = 0;

    /**
     * 排序字段
     */
    @JsonView(CommonJsonView.SimpleView.class)
    private Integer easySort;
}
