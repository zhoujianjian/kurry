package com.zj.nacos.one;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zhoujian
 * @Date: 2019/9/29 11:36
 * @Company: youanmi.
 * @Desc:
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosBootUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosBootUserApplication.class, args);
    }

}
