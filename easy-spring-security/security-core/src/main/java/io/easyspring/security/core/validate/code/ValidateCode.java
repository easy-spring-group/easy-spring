package io.easyspring.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 验证码信息封装类
 *
 * @author summer
 * @date 2019-01-16 23:39
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidateCode implements Serializable {

    /**
     * 验证码
     */
	private String code;
    /**
     * 到期时间
     */
	private LocalDateTime expireTime;
	
    /**
     * 根据过期时间封装的验证码对象
     *
     * @author summer
     * @date 2019-01-16 23:41
     * @param code 验证码
     * @param expire 有效时长(秒)
     * @version V1.0.0-RELEASE
     */
	public ValidateCode(String code, int expire){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expire);
	}

    /**
     * 校验验证码是否已经过期
     *
     * @author summer
     * @date 2019-01-17 14:52
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
