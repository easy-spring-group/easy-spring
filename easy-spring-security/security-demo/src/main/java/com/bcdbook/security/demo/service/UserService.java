package io.easyspring.security.demo.service;

import io.easyspring.framework.base.service.BaseService;
import io.easyspring.security.demo.model.User;
import org.springframework.validation.annotation.Validated;

/**
 * 用户 服务类
 *
 * @author summer
 * @date 2019-01-08
 * @version V1.0.0-RELEASE
 */
@Validated
public interface UserService extends BaseService<User> {

}
