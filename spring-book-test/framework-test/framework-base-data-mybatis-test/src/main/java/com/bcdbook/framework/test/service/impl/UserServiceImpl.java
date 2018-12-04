package com.bcdbook.framework.test.service.impl;

import com.bcdbook.framework.base.service.impl.BaseServiceImpl;
import com.bcdbook.framework.test.mapper.UserMapper;
import com.bcdbook.framework.test.model.User;
import com.bcdbook.framework.test.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户对象的 service 实现类
 *
 * @author summer
 * @date 2018-12-03 00:38
 * @version V1.0.0-RELEASE
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
