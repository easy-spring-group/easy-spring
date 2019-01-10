package com.bcdbook.framework.common.exception;

import com.bcdbook.framework.common.enums.ErrorResultEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 公共的异常类
 *
 * @author summer
 * @date 2019-01-07 16:55
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Getter
public class CommonException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误详情
     */
    private Object details;

    /**
     * 公共异常类的构造方法
     *
     * @author summer
     * @date 2019-01-07 16:54
     * @param errorResultEnum 错误信息枚举
     * @version V1.0.0-RELEASE
     */
    public CommonException(ErrorResultEnum errorResultEnum){
        super(errorResultEnum.getErrorMessage());
        this.code = errorResultEnum.getCode();
    }

    /**
     * 公共异常类的构造方法
     *
     * @author summer
     * @date 2019-01-07 16:54
     * @param errorResultEnum 错误信息枚举
     * @param details 错误详细信息
     * @version V1.0.0-RELEASE
     */
    public CommonException(ErrorResultEnum errorResultEnum, Object details){
        super(errorResultEnum.getErrorMessage());
        this.code = errorResultEnum.getCode();
        this.details = details;
    }

    /**
     * 通过错误码和提示信息封装的异常构造方法
     *
     * @author summer
     * @date 2019-01-07 16:55
     * @param message 错误信息
     * @version V1.0.0-RELEASE
     */
    public CommonException(String message) {
        super(message);
        this.code = ErrorResultEnum.ERROR.getCode();
    }

    protected void setCode(Integer code) {
        this.code = code;
    }

    protected void setDetails(Object details) {
        this.details = details;
    }
}
