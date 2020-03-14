package com.zj.spring.demo.dubbo.spi.jdk.service.impl;

import com.zj.spring.demo.dubbo.spi.jdk.service.Car;

/**
 * @ClassName: RedCar
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:15$
 * @Version: 1.0
 */
public class RedCar implements Car{
    @Override
    public void color() {
        System.out.println("wo shi  red car");
    }
}
