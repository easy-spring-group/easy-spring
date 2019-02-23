package io.easyspring.framework.common.result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装异常返回对象的接口
 *
 * @author summer
 * @date 2019-01-07 22:23
 * @version V1.0.0-RELEASE
 */
public interface ExceptionResultBuilder {

    String BEAN_NAME = "exceptionResultBuilder";

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
    ExceptionResult builder(HttpServletRequest request, HttpServletResponse response, Exception exception);
}
