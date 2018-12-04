package com.bcdbook.framework.test.service.impl;

import com.bcdbook.framework.test.model.User;
import com.bcdbook.framework.test.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户的 service 测试类
 *
 * @author summer
 * @date 2018-12-04 22:16
 * @version V1.0.0-RELEASE
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    /**
     * 注入用户的 service
     */
    @Resource
    private UserService userService;

    /**
     * 测试生成全局唯一 id 的方法
     *
     * @author summer
     * @date 2018-12-04 22:17
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void perInsert() {
        User user = new User();
        userService.perInsert(user);
        Assert.assertNotNull(user.getId());
        Assert.assertTrue(user.getId() > 0);
    }


    /**
     * 插入或更新数据的测试(测试插入)
     *
     * @author summer
     * @date 2018-12-04 22:30
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertOrUpdateSelectiveInsert(){
        String username = "summer";

        User user = new User();
        user.setName(username);

        User userResult = userService.insertOrUpdateSelective(user);
        Assert.assertNotNull(userResult);
        Assert.assertNotNull(userResult.getId());
        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(username, userResult.getName());
    }


    /**
     * 插入或更新数据的测试(测试修改)
     *
     * @author summer
     * @date 2018-12-04 22:30
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertOrUpdateSelectiveUpdate(){

        String username = "summer-2";
        Long userId = 1428117721710592L;

        User user = new User();
        user.setId(userId);
        user.setName(username);

        User userResult = userService.insertOrUpdateSelective(user);
        Assert.assertNotNull(userResult);

        Assert.assertNotNull(userResult.getId());
        Assert.assertEquals(userId, userResult.getId());

        Assert.assertEquals(username, userResult.getName());
    }

    /**
     * 插入或更新数据的测试(测试修改)
     *
     * @author summer
     * @date 2018-12-04 22:30
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertOrUpdateSelectiveUpdateNull(){

        String username = "summer-exception";
        // 此 id 是不存在的
        Long userId = 123L;

        User user = new User();
        user.setId(userId);
        user.setName(username);

        User userResult = userService.insertOrUpdateSelective(user);
        Assert.assertNull(userResult);
    }

    /**
     * 测试插入数据(全插入)
     *
     * @author summer
     * @date 2018-12-04 22:59
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertAll(){
        String name = "summer";

        User user = new User();
        user.setName(name);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        User userResult = userService.insertAll(user);
        Assert.assertNotNull(userResult);
        Assert.assertNotNull(userResult.getId());
        Assert.assertEquals(name, userResult.getName());
    }

    /**
     * 测试插入数据(选择性插入)
     *
     * @author summer
     * @date 2018-12-04 23:05
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void insertSelective(){
        String name = "summer";

        User user = new User();
        user.setName(name);

        User userResult = userService.insertSelective(user);
        Assert.assertNotNull(userResult);
        Assert.assertNotNull(userResult.getId());
        Assert.assertEquals(name, userResult.getName());
    }

    /**
     * 删除测试(正常删除)
     *
     * @author summer
     * @date 2018-12-04 23:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void delete(){
        Long id = 1435535537078272L;
        int deleted = userService.delete(id);
        Assert.assertTrue(deleted > 0);
    }

    /**
     * 删除测试(未找到数据)
     *
     * @author summer
     * @date 2018-12-04 23:07
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void deleteNoData(){
        // 此 id 是不存在的
        Long id = 123L;
        int deleted = userService.delete(id);
        Assert.assertTrue(deleted == 0);
    }

    /**
     * 按照条件删除(有数据)
     *
     * @author summer
     * @date 2018-12-04 23:11
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void deleteSelective(){
        User user = new User();
        user.setName("summer");
        int deleted = userService.deleteSelective(user);
        Assert.assertTrue(deleted > 0);
    }

    /**
     * 按照条件删除(无数据)
     *
     * @author summer
     * @date 2018-12-04 23:11
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void deleteSelectiveNoData(){
        User user = new User();
        user.setName("summer-no-data");
        int deleted = userService.deleteSelective(user);
        Assert.assertTrue(deleted == 0);
    }

}