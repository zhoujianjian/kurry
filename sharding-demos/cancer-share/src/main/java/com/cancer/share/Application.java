package com.cancer.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by zhoujian on 2018/6/29
 *
 * @Desc 类描述.
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.cancer.*"
})@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) //排除DataSourceConfiguratrion
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
