package com.bcdbook.security.browser;

import com.bcdbook.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 浏览器环境下安全配置主类
 * WebSecurityConfigurerAdapter 适配器类。专门用来做web应用的安全配置
 *
 * @author summer
 * @date 2019-01-16 12:56
 * @version V1.0.0-RELEASE
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 重写 configure 方法, 用于配置登录方式
     *
     * 最简单的修改默认配置的方法
     * 在v5+中，该配置（表单登录）应该是默认配置了
     * basic登录（也就是弹框登录的）应该是v5-的版本默认
     *
     * @author summer
     * @date 2019-01-16 12:59
     * @param http Security 的 Http 请求参数
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 最简单的修改默认配置的方法
        http
                // 定义表单登录 - 身份认证的方式
                .formLogin()
                // 指定登录页面
                .loginPage("/authentication/require")
                // 自己定义用于用户名密码登录的路径(实现逻辑 Security 已经实现)
                .loginProcessingUrl("/authentication/form")
                .and()
                // 对请求授权配置：注意方法名的含义，能联想到一些
                .authorizeRequests()
                // 放行这个路径
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage())
                .permitAll()
                // 任何请求
                .anyRequest()
                // 对任意请求都必须是已认证才能访问
                .authenticated()
                .and()
                // 去除跨站伪造防护
                .csrf().disable()
        ;
    }
}
