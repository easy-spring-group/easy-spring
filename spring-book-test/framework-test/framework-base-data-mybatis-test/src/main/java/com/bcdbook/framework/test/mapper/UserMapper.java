package com.bcdbook.framework.test.mapper;

import com.bcdbook.framework.base.mapper.BaseMapper;
import com.bcdbook.framework.test.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author summer
 * @date 2018-12-03 00:35
 * @version V1.0.0-RELEASE
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}