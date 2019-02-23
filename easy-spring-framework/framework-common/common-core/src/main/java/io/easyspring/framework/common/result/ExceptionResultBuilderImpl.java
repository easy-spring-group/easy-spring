package io.easyspring.framework.common.result;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装异常返回对象的实现类
 * 注意: 在覆盖这个方法的时候, 需要设定组件的名字为 ExceptionResultBuilder.beanName
 *
 * @author summer
 * @date 2019-01-07 22:51
 * @version V1.0.0-RELEASE
 */
@Component
@ConditionalOnMissingBean(name = ExceptionResultBuilder.BEAN_NAME)
public class ExceptionResultBuilderImpl implements ExceptionResultBuilder {

    /**
     * 封装异常返回对象
     *
     * @author summer
     * @date 2019-01-07 18:29
     * @param request 请求对象
     * @param response 返回对象
     * @param exception 异常信息
     * @return io.easyspring.framework.result.ExceptionResult
     * @version V1.0.0-RELEASE
     */
    @Override
    public ExceptionResult builder(HttpServletRequest request, HttpServletResponse response, Exception exception){
        // 创建错误返回信息
        ErrorResult errorResult = ErrorResult.error(ErrorResultEnum.ERROR);
        errorResult.setDetails(exception.getMessage());

        // 创建异常返回对象
        ExceptionResult exceptionResult = new ExceptionResult();
        // 设置错误码
        exceptionResult.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        // 设置错误返回信息
        exceptionResult.setErrorResult(errorResult);

        return exceptionResult;
    }
}
