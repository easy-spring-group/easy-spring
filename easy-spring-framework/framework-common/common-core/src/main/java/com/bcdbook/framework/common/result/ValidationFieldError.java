package com.bcdbook.framework.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据校验, 用于存储错误字段的实体
 *
 * @author summer
 * @date 2019-01-08 12:42
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidationFieldError {

    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 错误信息
     */
    private String errorMessage;
}
