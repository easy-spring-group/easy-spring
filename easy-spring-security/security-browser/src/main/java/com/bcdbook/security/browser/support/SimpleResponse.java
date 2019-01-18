package com.bcdbook.security.browser.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单返回对象
 *
 * @author summer
 * @date 2019-01-16 15:50
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleResponse {
    private Object content;
}
