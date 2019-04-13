package io.easyspring.framework.common.exception;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 公共的异常类
 *
 * @author summer
 * DateTime 2019-01-07 16:55
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
     * Author summer
     * DateTime 2019-01-07 16:54
     * @param errorResultEnum 错误信息枚举
     * Version V1.0.0-RELEASE
     */
    public CommonException(ErrorResultEnum errorResultEnum){
        super(errorResultEnum.getErrorMessage());
        this.code = errorResultEnum.getCode();
    }

    /**
     * 公共异常类的构造方法
     *
     * Author summer
     * DateTime 2019-01-07 16:54
     * @param errorResultEnum 错误信息枚举
     * @param details 错误详细信息
     * Version V1.0.0-RELEASE
     */
    public CommonException(ErrorResultEnum errorResultEnum, Object details){
        super(errorResultEnum.getErrorMessage());
        this.code = errorResultEnum.getCode();
        this.details = details;
    }

    /**
     * 通过错误码和提示信息封装的异常构造方法
     *
     * Author summer
     * DateTime 2019-01-07 16:55
     * @param message 错误信息
     * Version V1.0.0-RELEASE
     */
    public CommonException(String message) {
        super(message);
        this.code = ErrorResultEnum.ERROR.getCode();
    }

    /**
     * 可以设定错误详情的构建方法
     *
     * @param message 错误消息
     * @param details 错误详情
     * @return
     * Author summer
     * Version V1.0.0-RELEASE
     * DateTime 2019-04-13 18:39
     */
    public CommonException(String message, Object details){
        super(message);
        this.code = ErrorResultEnum.ERROR.getCode();
        this.details = details;
    }

    protected void setCode(Integer code) {
        this.code = code;
    }

    protected void setDetails(Object details) {
        this.details = details;
    }
}
