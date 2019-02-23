package io.easyspring.framework.common.result;

import lombok.Data;

/**
 * 错误返回信息的抽象类
 *
 * @author summer
 * @date 2019-01-07 15:30
 * @version V1.0.0-RELEASE
 */
@Data
public abstract class ErrorResultAbstract {
    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 错误详情
     */
    private Object details;
}
