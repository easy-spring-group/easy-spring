package com.bcdbook.framework.test.web;

import com.bcdbook.framework.test.model.User;
import com.bcdbook.framework.test.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户对象的 controller
 *
 * @author summer
 * @date 2018-12-03 00:39
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
     * @author summer
     * @date 2018-12-03 00:40
     * @param user 想要添加的用户对象
     * @return com.bcdbook.framework.test.model.User
     * @version V1.0.0-RELEASE
     */
    @PostMapping
    public User add(@RequestBody User user){
        return userService.insertSelective(user);
    }
}
