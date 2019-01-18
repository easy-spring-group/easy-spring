package com.bcdbook.security.properties;

import com.bcdbook.security.enums.ResultTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 浏览器的配置类
 *
 * @author summer
 * @date 2019-01-16 14:22
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
public class BrowserProperties {

    /** 登录页面路径 */
    private String loginPage = "/signIn.html";
    /** 登录成功/失败后是跳转还是返回 json (默认是返回 json)*/
    private ResultTypeEnum resultType = ResultTypeEnum.JSON;
}
