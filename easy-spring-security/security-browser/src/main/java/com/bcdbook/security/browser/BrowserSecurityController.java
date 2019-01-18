package com.bcdbook.security.browser;

import com.bcdbook.framework.common.enums.ErrorResultEnum;
import com.bcdbook.framework.common.result.ErrorResult;
import com.bcdbook.framework.common.utils.HttpRequestUtils;
import com.bcdbook.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 浏览器安全请求的控制类
 *
 * @author summer
 * @date 2019-01-16 14:48
 * @version V1.0.0-RELEASE
 */
@RestController
@Slf4j
public class BrowserSecurityController {
    /**
     * request 信息缓存的工具类
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * spring 的跳转策略工具类
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时跳转到这里
     *
     * @author summer
     * @date 2019-01-16 14:47
     * @param request 请求信息
     * @param response 返回信息
     * @return void
     * @version V1.0.0-RELEASE
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorResult requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转认证的请求：" + targetUrl);

            boolean isHtml = HttpRequestUtils.isHtml(request);
            boolean endsWithHtml = StringUtils.endsWithIgnoreCase(targetUrl, ".html");
            if (!isHtml && !endsWithHtml) {
                return ErrorResult.error(ErrorResultEnum.USER_NOT_LOGIN_ERROR);
            }
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
        }

        return ErrorResult.error(ErrorResultEnum.USER_NOT_LOGIN_ERROR);
    }
}
