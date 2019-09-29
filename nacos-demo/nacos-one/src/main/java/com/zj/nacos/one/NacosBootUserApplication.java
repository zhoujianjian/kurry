package com.zj.nacos.one;

import org.apache.dubbo.spring.boot.actuate.endpoint.metadata.DubboShutdownMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zhoujian
 * @Date: 2019/9/29 11:36
 * @Company: youanmi.
 * @Desc:
 */
@SpringBootApplication
 public class NacosBootUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosBootUserApplication.class, args);
    }

}
