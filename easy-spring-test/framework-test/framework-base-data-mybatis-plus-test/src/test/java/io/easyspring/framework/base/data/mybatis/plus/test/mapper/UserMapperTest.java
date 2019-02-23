package io.easyspring.framework.base.data.mybatis.plus.test.mapper;

import io.easyspring.framework.base.data.mybatis.plus.test.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        User userCondition = new User();
        userCondition.setName("summer");

        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(3, userList.size());
        userList.forEach(System.out::println);
    }
}