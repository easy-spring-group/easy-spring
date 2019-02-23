package io.easyspring.framework.common.result;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import io.easyspring.framework.common.exception.CommonException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 错误返回信息的基础实现类
 *
 * @author summer
 * @date 2019-01-07 15:35
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ErrorResult extends ErrorResultAbstract {

    /**
     * 根据传入的异常信息对象, 创建错误返回对象的方法
     *
     * @author summer
     * @date 2019-01-07 16:12
     * @param commonException 异常信息对象
     * @return io.easyspring.framework.result.ErrorResult
     * @version V1.0.0-RELEASE
     */
    public static ErrorResult error(CommonException commonException){
        // 封装错误返回信息并返回
        return getErrorResult(commonException.getCode(), commonException.getMessage(),
                commonException.getDetails());
    }

    /**
     * 根据传入的错误信息的枚举, 创建错误返回对象的方法
     *
     * @author summer
     * @date 2019-01-07 16:12
     * @param errorResultEnum 错误信息的枚举
     * @return io.easyspring.framework.result.ErrorResult
     * @version V1.0.0-RELEASE
     */
    public static ErrorResult error(ErrorResultEnum errorResultEnum){
        // 封装错误返回信息并返回
        return getErrorResult(errorResultEnum.getCode(), errorResultEnum.getErrorMessage(), errorResultEnum.getDetails());
    }

    /**
     * 根据传入的错误信息, 创建默认的错误返回信息
     *
     * @author summer
     * @date 2019-01-07 16:09
     * @param errorMessage 错误消息
     * @return io.easyspring.framework.result.ErrorResult
     * @version V1.0.0-RELEASE
     */
    public static ErrorResult error(String errorMessage) {
        // 创建错误返回信息
        return getErrorResult(ErrorResultEnum.ERROR.getCode(), errorMessage, null);
    }

    /**
     * 根据传入的错误信息和错误详情, 创建默认的错误返回信息
     *
     * @author summer
     * @date 2019-01-07 16:09
     * @param errorMessage 错误消息
     * @param details 错误详情
     * @return io.easyspring.framework.result.ErrorResult
     * @version V1.0.0-RELEASE
     */
    public static ErrorResult error(String errorMessage, Object details) {
        // 创建错误返回信息
        return getErrorResult(ErrorResultEnum.ERROR.getCode(), errorMessage, details);
    }

    /**
     * 根据传入的信息, 创建错误返回信息对象
     *
     * @author summer
     * @date 2019-01-07 15:50
     * @param errorCode 错误码
     * @param errorMessage 错误信息
     * @param details 错误详情
     * @return R
     * @version V1.0.0-RELEASE
     */
    private static ErrorResult getErrorResult(Integer errorCode,
                                         String errorMessage,
                                         Object details) {
        // 创建错误返回对象
        ErrorResult errorResult = new ErrorResult();
        // 设置通用的错误码
        errorResult.setErrorCode(errorCode);
        // 设置错误信息
        errorResult.setErrorMessage(errorMessage);
        // 设置错误详情
        errorResult.setDetails(details);

        return errorResult;
    }
}
