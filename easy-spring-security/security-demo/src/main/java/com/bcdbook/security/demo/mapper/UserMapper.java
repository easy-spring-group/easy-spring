package io.easyspring.security.demo.mapper;

import io.easyspring.framework.base.mapper.BaseMapper;
import io.easyspring.security.demo.model.User;
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
