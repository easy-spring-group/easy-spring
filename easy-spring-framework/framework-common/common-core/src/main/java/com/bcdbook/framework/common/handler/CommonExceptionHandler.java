package com.bcdbook.framework.common.handler;

import com.bcdbook.framework.common.properties.CommonExceptionProperties;
import com.bcdbook.framework.common.result.ErrorResultAbstract;
import com.bcdbook.framework.common.result.ExceptionResult;
import com.bcdbook.framework.common.result.ExceptionResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
 * @date 2019-01-07 17:06
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
     * @author summer
     * @date 2019-01-07 17:05
     * @param request 请求对象
     * @param response 返回对象
     * @param exception 异常
     * @return com.bcdbook.framework.result.ErrorResultAbstract
     * @version V1.0.0-RELEASE
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
     * @author summer
     * @date 2019-01-07 18:12
     * @param request 请求对象
     * @param response 返回对象
     * @param exception 异常信息
     * @return java.lang.Object
     * @version V1.0.0-RELEASE
     */
    private Object getExceptionResult(HttpServletRequest request, HttpServletResponse response, Exception exception) {

        // 获取异常返回对象
        ExceptionResult exceptionResult = exceptionResultBuilder.builder(request, response, exception);

        // 检查是否需要返回 json
        boolean isHtml = isHtml(request);

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
     * @author summer
     * @date 2019-01-07 18:16
     * @param response 返回对象
     * @param exceptionResult 异常返回对象
     * @return com.bcdbook.framework.result.ErrorResultAbstract
     * @version V1.0.0-RELEASE
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
     * @author summer
     * @date 2019-01-07 18:17
     * @param exceptionResult 异常返回对象
     * @return org.springframework.web.servlet.ModelAndView
     * @version V1.0.0-RELEASE
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
//     * @author summer
//     * @date 2019-01-07 18:29
//     * @param request 请求对象
//     * @param response 返回对象
//     * @param exception 异常信息
//     * @return com.bcdbook.framework.result.ExceptionResult
//     * @version V1.0.0-RELEASE
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

    /**
     * 校验当前请求是否是 ajax 请求
     *
     * @author summer
     * @date 2019-01-07 17:34
     * @param request 请求对象
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    private boolean isHtml(HttpServletRequest request){
        // 传入参数校验
        if (request == null) {
            return false;
        }

        // 从请求头中获取 内容类型
        String accept = request.getHeader("Accept");
        // 如果请求内容不为空, 并且包含 json 则说明是 ajax 请求
        return !StringUtils.isEmpty(accept) && accept.contains("text/html");
    }
}
