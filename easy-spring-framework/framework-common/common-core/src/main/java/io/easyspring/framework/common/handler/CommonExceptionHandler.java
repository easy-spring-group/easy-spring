package io.easyspring.framework.common.handler;

import io.easyspring.framework.common.properties.CommonExceptionProperties;
import io.easyspring.framework.common.result.ErrorResultAbstract;
import io.easyspring.framework.common.result.ExceptionResult;
import io.easyspring.framework.common.result.ExceptionResultBuilder;
import io.easyspring.framework.common.utils.HttpRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共异常类的过滤处理器
 *
 * @author summer
 * DateTime 2019-01-07 17:06
 * @version V1.0.0-RELEASE
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 注入异常的配置对象
     */
    @Resource
    private CommonExceptionProperties commonExceptionProperties;

    /**
     * 注入异常返回对象的封装类
     */
    @Resource
    private ExceptionResultBuilder exceptionResultBuilder;

    /**
     * 异常统一拦截的过滤器
     *
     * Author summer
     * DateTime 2019-01-07 17:05
     * @param request 请求对象
     * @param response 返回对象
     * @param exception 异常
     * @return io.easyspring.framework.result.ErrorResultAbstract
     * Version V1.0.0-RELEASE
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandle(HttpServletRequest request,
                                               HttpServletResponse response,
                                               Exception exception) {
         log.error("[异常拦截] exception: {}", exception);

        // 封装并返回异常信息
        return getExceptionResult(request, response, exception);
    }

    /**
     * 封装出现异常时返回的信息
     *
     * Author summer
     * DateTime 2019-01-07 18:12
     * @param request 请求对象
     * @param response 返回对象
     * @param exception 异常信息
     * @return java.lang.Object
     * Version V1.0.0-RELEASE
     */
    private Object getExceptionResult(HttpServletRequest request, HttpServletResponse response, Exception exception) {

        // 获取异常返回对象
        ExceptionResult exceptionResult = exceptionResultBuilder.builder(request, response, exception);

        // 检查是否需要返回 json
        boolean isHtml = HttpRequestUtils.isHtml(request);

        /*
         * 如果是需要返回 json
         */
        if (isHtml) {
            return getErrorView(exceptionResult);
        } else {
            return getErrorResult(response, exceptionResult);
        }
    }

    /**
     * 封装错误返回信息对象
     *
     * Author summer
     * DateTime 2019-01-07 18:16
     * @param response 返回对象
     * @param exceptionResult 异常返回对象
     * @return io.easyspring.framework.result.ErrorResultAbstract
     * Version V1.0.0-RELEASE
     */
    private ErrorResultAbstract getErrorResult(HttpServletResponse response, ExceptionResult exceptionResult){
        // 设置返回状态码
        response.setStatus(exceptionResult.getHttpStatus().value());

        // 返回封装好的错误信息
        return exceptionResult.getErrorResult();
    }

    /**
     * 封装错误返回信息的页面
     *
     * Author summer
     * DateTime 2019-01-07 18:17
     * @param exceptionResult 异常返回对象
     * @return org.springframework.web.servlet.ModelAndView
     * Version V1.0.0-RELEASE
     */
    private ModelAndView getErrorView(ExceptionResult exceptionResult){

        // 创建模型视图
        ModelAndView modelAndView = new ModelAndView();
        // 设置视图状态
        modelAndView.setStatus(exceptionResult.getHttpStatus());
        // 设置视图模板
        modelAndView.setViewName(commonExceptionProperties.getErrorPage());
        // 添加异常参数
        modelAndView.addObject(commonExceptionProperties.getExceptionName(), exceptionResult.getErrorResult());

        return modelAndView;
    }

//    /**
//     * 封装异常返回对象
//     *
//     * Author summer
//     * DateTime 2019-01-07 18:29
//     * @param request 请求对象
//     * @param response 返回对象
//     * @param exception 异常信息
//     * @return io.easyspring.framework.result.ExceptionResult
//     * Version V1.0.0-RELEASE
//     */
//    private ExceptionResult buildExceptionResult(HttpServletRequest request,
//                                                 HttpServletResponse response,
//                                                 Exception exception){
//        // 创建错误返回新
//        ErrorResult errorResult = ErrorResult.error("用于测试的错误");
//
//        // 创建异常返回对象
//        ExceptionResult exceptionResult = new ExceptionResult();
//        // 设置错误码
//        exceptionResult.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        // 设置错误返回信息
//        exceptionResult.setErrorResult(errorResult);
//
//        return exceptionResult;
//    }


}
