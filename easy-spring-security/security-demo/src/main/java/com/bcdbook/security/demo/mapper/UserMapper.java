package com.bcdbook.security.demo.mapper;

import com.bcdbook.framework.base.mapper.BaseMapper;
import com.bcdbook.security.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 用户 Mapper 接口
 *
 * @author summer
 * @date 2019-01-08
 * @version V1.0.0-RELEASE
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
