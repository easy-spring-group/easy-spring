package com.bcdbook.security.demo.model;

import com.bcdbook.framework.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

/**
 * 用户实体
 *
 * @author summer
 * @date 2019-01-15 18:38
 * @version V1.0.0-RELEASE
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_user")
public class User extends BaseModel {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * open id
     */
    private String openId;
    /**
     * 头像
     */
    private String headImgUrl;
    /**
     * 手机号
     */
    private String telephone;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 是否已经锁定
     */
    private Boolean locked;

}
