package com.bcdbook.security.demo.service.impl;

import com.bcdbook.framework.base.service.impl.BaseServiceImpl;
import com.bcdbook.security.demo.mapper.UserMapper;
import com.bcdbook.security.demo.model.User;
import com.bcdbook.security.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务实现类
 *
 * @author summer
 * @date 2019-01-08
 * @version V1.0.0-RELEASE
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
