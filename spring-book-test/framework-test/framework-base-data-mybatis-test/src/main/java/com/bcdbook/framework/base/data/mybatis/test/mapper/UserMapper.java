package com.bcdbook.framework.base.data.mybatis.test.mapper;

import com.bcdbook.framework.base.data.mybatis.test.model.User;
import com.bcdbook.framework.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 用户对象的 mapper
 *
 * @author summer
 * @date 2018-12-03 00:35
 * @version V1.0.0-RELEASE
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}