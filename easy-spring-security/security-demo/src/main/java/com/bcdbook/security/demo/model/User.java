package io.easyspring.security.demo.model;

import io.easyspring.framework.base.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
public class User extends BaseModel implements UserDetails {

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

    @Transient
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * 账户是否是未过期的
     *
     * @author summer
     * @date 2019-01-16 13:12
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否是未锁定
     *
     * @author summer
     * @date 2019-01-16 13:10
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    /**
     * 密码是否是未过期的
     *
     * @author summer
     * @date 2019-01-16 13:11
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否是启用状态
     *
     * @author summer
     * @date 2019-01-16 13:11
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    @Override
    public boolean isEnabled() {
        return !this.getDeleted();
    }
}
