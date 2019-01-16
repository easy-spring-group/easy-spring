package com.bcdbook.security.demo.service.impl;

import com.bcdbook.framework.base.service.impl.BaseServiceImpl;
import com.bcdbook.security.demo.mapper.UserMapper;
import com.bcdbook.security.demo.model.User;
import com.bcdbook.security.demo.service.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户 服务实现类
 *
 * @author summer
 * @date 2019-01-08
 * @version V1.0.0-RELEASE
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    /**
     * 重写 Security 中 UserDetailsService 类的方法, 根据用户名获取用户对象
     * 注: 用户对象需要实现 Security 的 UserDetails 接口, 并实现接口中对应的方法
     *
     * @author summer
     * @date 2019-01-16 13:24
     * @param username 传入的用户名
     * @return com.bcdbook.security.demo.model.User
     * @version V1.0.0-RELEASE
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);

        User userResult = getByParameters(user);

        userResult.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return userResult;
    }
}