package com.bcdbook.security.browser.authentication;

import com.bcdbook.framework.common.enums.ErrorResultEnum;
import com.bcdbook.framework.common.result.ErrorResult;
import com.bcdbook.security.enums.ResultTypeEnum;
import com.bcdbook.security.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ${desc}
 * @author zhuqiang
 * @version 1.0.1 2018/8/3 16:42
 * @date 2018/8/3 16:42
 * @since 1.0
 */
@Component("easyAuthenticationFailureHandler")
@Slf4j
public class EasyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * 注入 Jackson 的 Mapper
     */
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 注入通用的配置类
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 重写过滤器实现
     *
     * @author summer
     * @date 2019-01-16 18:57
     * @param request 请求对象
     * @param response 返回对象
     * @param exception 登录出错后的异常信息
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        logger.info("登录失败");

        // 如果配置的返回类型是 json ,则封装 json 信息并返回
        if (securityProperties.getBrowser().getResultType() == ResultTypeEnum.JSON) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            // 封装自己的异常信息
            com.bcdbook.framework.common.exception.AuthenticationException authenticationException =
                    new com.bcdbook.framework.common.exception.AuthenticationException(
                            ErrorResultEnum.USER_LOGIN_ERROR.getErrorMessage(),
                            exception.getMessage());

            // 封装错误返回对象
            ErrorResult errorResult = ErrorResult.error(authenticationException);
            // 返回错误信息
            response.getWriter().write(objectMapper.writeValueAsString(errorResult));
        } else {
            // 否则直接调用父类的返回(跳转)
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
