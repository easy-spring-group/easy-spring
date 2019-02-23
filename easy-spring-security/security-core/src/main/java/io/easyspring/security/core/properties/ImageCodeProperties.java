package io.easyspring.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片验证码配置项
 *
 * @author summer
 * @date 2019-01-16 20:53
 * @version V1.0.0-RELEASE
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    /**
     * 图片验证码的空参构造(默认长度为 4)
     *
     * @author summer
     * @date 2019-01-16 20:54
     * @version V1.0.0-RELEASE
     */
	public ImageCodeProperties() {
		setLength(4);
	}
	
	/**
	 * 图片宽
	 */
	private int width = 67;
	/**
	 * 图片高
	 */
	private int height = 23;
}
