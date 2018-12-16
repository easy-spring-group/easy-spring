package com.bcdbook.framework.base.data.mybatis.test.service.impl;

import com.bcdbook.framework.base.data.mybatis.test.mapper.SortUserMapper;
import com.bcdbook.framework.base.data.mybatis.test.model.SortUser;
import com.bcdbook.framework.base.data.mybatis.test.service.SortUserService;
import com.bcdbook.framework.base.service.impl.ExtensionBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户对象的 service 实现类
 *
 * @author summer
 * @date 2018-12-03 00:38
 * @version V1.0.0-RELEASE
 */
@Service
public class SortUserServiceImpl extends ExtensionBaseServiceImpl<SortUserMapper, SortUser> implements SortUserService {
}
