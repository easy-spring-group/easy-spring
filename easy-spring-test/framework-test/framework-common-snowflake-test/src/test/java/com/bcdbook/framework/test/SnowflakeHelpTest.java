package com.bcdbook.framework.test;

import com.bcdbook.framework.common.snowflake.SnowflakeHelp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 获取全局唯一 id 的测试类
 *
 * @author summer
 * @date 2018-12-03 18:52
 * @version V1.0.0-RELEASE
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SnowflakeHelpTest {

    /**
     * 注入生成唯一 id 的工具类
     */
    @Resource
    private SnowflakeHelp snowflakeHelp;

    /**
     * 测试全局唯一 id 生成
     *
     * @author summer
     * @date 2018-12-03 19:11
     * @return void
     * @version V1.0.0-RELEASE
     */
    @Test
    public void getNextIdTest(){
        for (int i = 0; i < 100; i++) {
            long nextId = snowflakeHelp.nextId();
            Assert.assertTrue(nextId > 0);
        }
    }
}
