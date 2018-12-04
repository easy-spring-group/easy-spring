package com.bcdbook.framework.test;

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
        "com.bcdbook.framework.base",
        "com.bcdbook.framework.common",
        "com.bcdbook.framework.test"
})
public class FrameworkBaseDataMybatisTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkBaseDataMybatisTestApplication.class, args);
    }
}
