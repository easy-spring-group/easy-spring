package io.easyspring.message;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 消息的异常对象
 *
 * @author summer
 * DateTime 2019-03-13 12:20
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Getter
public class EasyMessageException extends RuntimeException {

    private static final long serialVersionUID = 9197475678541128054L;

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误详情
     */
    private Object details;

    /**
     * 消息的异常对象的构造方法
     *
     * Author summer
     * DateTime 2019-01-07 16:54
     * @param errorResultEnum 错误信息枚举
     * Version V1.0.0-RELEASE
     */
    public EasyMessageException(ErrorResultEnum errorResultEnum){
        super(errorResultEnum.getErrorMessage());
        this.code = errorResultEnum.getCode();
    }

    /**
     * 消息的异常对象的构造方法
     *
     * Author summer
     * DateTime 2019-01-07 16:54
     * @param errorResultEnum 错误信息枚举
     * @param details 错误详细信息
     * Version V1.0.0-RELEASE
     */
    public EasyMessageException(ErrorResultEnum errorResultEnum, Object details){
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
    public EasyMessageException(String message) {
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
