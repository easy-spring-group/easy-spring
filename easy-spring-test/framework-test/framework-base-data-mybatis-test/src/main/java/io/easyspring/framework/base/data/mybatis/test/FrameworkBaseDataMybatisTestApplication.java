package io.easyspring.framework.base.data.mybatis.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO
 *
 * @author summer
 * @date 2018-12-03 00:34
 * @version V1.0.0-RELEASE
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "io.easyspring.framework.base",
        "io.easyspring.framework.common",
        "io.easyspring.framework.test"
})
public class FrameworkBaseDataMybatisTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkBaseDataMybatisTestApplication.class, args);
    }
}
