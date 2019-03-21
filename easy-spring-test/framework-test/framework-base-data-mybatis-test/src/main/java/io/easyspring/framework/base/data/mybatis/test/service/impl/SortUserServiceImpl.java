package io.easyspring.framework.base.data.mybatis.test.service.impl;

import io.easyspring.framework.base.data.mybatis.test.mapper.SortUserMapper;
import io.easyspring.framework.base.data.mybatis.test.model.SortUser;
import io.easyspring.framework.base.data.mybatis.test.service.SortUserService;
import io.easyspring.framework.base.service.impl.ExtensionBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户对象的 service 实现类
 *
 * @author summer
 * DateTime 2018-12-03 00:38
 * @version V1.0.0-RELEASE
 */
@Service
public class SortUserServiceImpl extends ExtensionBaseServiceImpl<SortUserMapper, SortUser> implements SortUserService {
}
