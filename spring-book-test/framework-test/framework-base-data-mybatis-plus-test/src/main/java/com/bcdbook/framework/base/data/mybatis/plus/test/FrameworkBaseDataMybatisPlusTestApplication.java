package com.bcdbook.framework.base.data.mybatis.plus.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主启动入口
 *
 * @author summer
 * @date 2018-12-05 12:58
 * @annotation @MapperScan 扫描 Mapper 文件夹
 * @version V1.0.0-RELEASE
 */
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class FrameworkBaseDataMybatisPlusTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkBaseDataMybatisPlusTestApplication.class, args);
    }
}
