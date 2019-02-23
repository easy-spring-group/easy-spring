package io.easyspring.security.core.validate.code.image;


import io.easyspring.security.core.validate.code.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


/**
 * 图片验证码
 *
 * @author summer
 * @date 2019-01-16 23:43
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ImageCode extends ValidateCode {

    /**
     * 图片对象
     */
	private BufferedImage image; 
	
    /**
     * 根据有效时长生成的图片验证码对象
     *
     * @author summer
     * @date 2019-01-16 23:46
     * @param image 图片对象
     * @param code 验证码
     * @param expire 有效时长
     * @version V1.0.0-RELEASE
     */
	public ImageCode(BufferedImage image, String code, int expire){
		super(code, expire);
		this.image = image;
	}

    /**
     * 全参构造
     * @param image 图片对象
     * @param code 验证码
     * @param expireTime 到期时间
     */
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
}
