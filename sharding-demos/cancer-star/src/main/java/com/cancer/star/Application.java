package com.cancer.star;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 /**
 * @Auther: zhoujian
 * @Date: 2018/7/18 16:53
 * @Company:
 * @Desc:项目启动类
 */
@ComponentScan(basePackages = {"com.cancer.star.*"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EnableCaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
