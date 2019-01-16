package com.bcdbook.security.demo.controller;

import com.bcdbook.security.demo.model.User;
import com.bcdbook.security.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.List;

/**
 * 用户的 controller
 *
 * @author summer
 * @date 2019-01-15 18:40
 * @version V1.0.0-RELEASE
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户查询的方法
     *
     * @author summer
     * @date 2018-12-18 11:06
     * @param user 查询条件
     * @param pageable 分页信息
     * @return java.util.List<com.bcdbook.security.demo.entity.User>
     * @version V1.0.0-RELEASE
     */
    @GetMapping
    public List<User> query(User user,
                            @PageableDefault(page = 1, size = 12, sort = "username,asc") Pageable pageable) {

        // 使用反射工具, 打印对应的对象
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        return userService.list(user);
    }

    /**
     * 获取用户详情的接口
     *
     * @author summer
     * @date 2018-12-18 23:03
     * @param id 用户 id
     * @return com.bcdbook.security.demo.entity.User
     * @version V1.0.0-RELEASE
     */
    @GetMapping("/{id:\\d+}")
    public User getInfo(@PathVariable Long id) {

        log.info("进入 getInfo 服务, 想要获取的用户是: {}", id);

        return userService.get(id);
    }

    /**
     * 创建用户的接口
     *
     * @author summer
     * @date 2018-12-18 23:19
     * @param user 用户对象
     * @return com.bcdbook.security.demo.entity.User
     * @version V1.0.0-RELEASE
     */
    @PostMapping
    public User create(@Valid @RequestBody User user) {

        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        return userService.insertOrUpdateSelective(user);
    }

    /**
     * 修改用户的接口
     *
     * @author summer
     * @date 2018-12-19 11:42
     * @param user 用户对象
     * @param errors 数据校验信息
     * @return com.bcdbook.security.demo.entity.User
     * @version V1.0.0-RELEASE
     */
    @PutMapping
    public User update(@Valid @RequestBody User user, BindingResult errors) {

        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));

        return userService.updateSelective(user);
    }

    /**
     * 删除服务
     *
     * @author summer
     * @date 2018-12-19 13:37
     * @param id 需要删除对象的 id
     * @return void
     * @version V1.0.0-RELEASE
     */
    @DeleteMapping("/{id:\\d+}")
    public boolean delete(@PathVariable Long id) {
        log.info("将要删除用户: {}", id);

        return userService.delete(id) > 0;
    }

    /**
     * 获取当前登录用户的接口
     *
     * @author summer
     * @date 2019-01-15 18:47
     * @param user SpringSecurity 存储的用户对象
     * @param request request 请求
     * @return java.lang.Object
     * @version V1.0.0-RELEASE
     */
    @GetMapping("/me")
    public Object getCurrentUser(Authentication user, HttpServletRequest request)
            throws SignatureException, IllegalArgumentException, UnsupportedEncodingException {

//		String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
//
//		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
//					.parseClaimsJws(token).getBody();
//
//		String company = (String) claims.get("company");
//
//		System.out.println(company);

        return user;
    }
}
