package io.easyspring.framework.base.data.mybatis.test.service.impl;

import io.easyspring.framework.base.data.mybatis.test.model.User;
import io.easyspring.framework.base.service.impl.BaseServiceImpl;
import io.easyspring.framework.base.data.mybatis.test.mapper.UserMapper;
import io.easyspring.framework.base.data.mybatis.test.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户对象的 service 实现类
 *
 * @author summer
 * DateTime 2018-12-03 00:38
 * @version V1.0.0-RELEASE
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
