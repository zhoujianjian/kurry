package com.zj.spring.demo.dubbo.spi.jdk.service.impl;

import com.zj.spring.demo.dubbo.spi.jdk.service.Car;

/**
 * @ClassName: BlackCar
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:20$
 * @Version: 1.0
 */
public class BlackCar implements Car {
    @Override
    public void color() {
        System.out.println("wo shi black car");
    }
}
