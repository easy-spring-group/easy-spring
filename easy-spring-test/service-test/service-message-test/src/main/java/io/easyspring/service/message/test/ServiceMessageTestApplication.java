package io.easyspring.service.message.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 消息测试的主启动类
 *
 * @author summer
 * DateTime 2019-03-19 17:54
 * @version V1.0.0-RELEASE
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "io.easyspring.framework.common",
        "io.easyspring.service.message"
})
public class ServiceMessageTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMessageTestApplication.class, args);
	}

}
