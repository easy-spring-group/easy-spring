package com.bcdbook.security.core.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简单响应的封装类
 *
 * @author summer
 * @date 2019-01-17 18:05
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleResponse {

    /**
     * 返回的内容
     */
	private Object content;
}
