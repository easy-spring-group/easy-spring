package io.easyspring.framework.base.data.mybatis.test.web;

import io.easyspring.framework.base.data.mybatis.test.model.User;
import io.easyspring.framework.base.data.mybatis.test.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户对象的 controller
 *
 * @author summer
 * DateTime 2018-12-03 00:39
 * @version V1.0.0-RELEASE
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 添加用户的方法
     *
     * Author summer
     * DateTime 2018-12-03 00:40
     * @param user 想要添加的用户对象
     * @return io.easyspring.framework.test.model.User
     * Version V1.0.0-RELEASE
     */
    @PostMapping
    public User add(@RequestBody User user){
        return userService.insertSelective(user);
    }
}
