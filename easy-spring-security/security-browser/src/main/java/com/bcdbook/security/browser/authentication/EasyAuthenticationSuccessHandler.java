package com.bcdbook.security.browser.authentication;

import com.bcdbook.security.enums.ResultTypeEnum;
import com.bcdbook.security.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * .formLogin().successHandler() 中需要的处理器类型
 * @author zhuqiang
 * @version 1.0.1 2018/8/3 16:29
 * @date 2018/8/3 16:29
 * @since 1.0
 */
@Component("easyAuthenticationSuccessHandler")
@Slf4j
public class EasyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * spring 是使用jackson来进行处理返回数据的
     * 所以这里可以得到他的实例
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 注入配置文件类
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @param request
     * @param response
     * @param authentication 封装了所有的认证信息
     * @throws IOException
     * @throws ServletException
     */
    /**
     * 重写登录成功的处理
     *
     * @author summer
     * @date 2019-01-16 19:03
     * @param request 请求信息
     * @param response 返回信息
     * @param authentication 登录的用户信息
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        log.info("登录成功");

        // 如果返回类型是 json 则封装成 json 并返回
        if (securityProperties.getBrowser().getResultType() == ResultTypeEnum.JSON) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            // 把本类实现父类改成 AuthenticationSuccessHandler 的子类 SavedRequestAwareAuthenticationSuccessHandler
            // 之前说spring默认成功是跳转到登录前的url地址
            // 就是使用的这个类来处理的
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
