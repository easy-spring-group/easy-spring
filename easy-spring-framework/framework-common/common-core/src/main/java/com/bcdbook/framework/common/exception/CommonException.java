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
     * 通过错误码和提示信息封装的异常构造方法
     *
     * @author summer
     * @date 2019-01-07 16:55
     * @param code 错误码
     * @param message 错误信息
     * @version V1.0.0-RELEASE
     */
    public CommonException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
