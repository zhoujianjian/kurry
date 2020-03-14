package com.zj.spring.demo.dubbo.spi.jdk.service;

import java.util.ServiceLoader;

/**
 * @ClassName: JdkTest
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:25$
 * @Version: 1.0
 */
public class JdkTest {


    public static void main(String[] args) {
        ServiceLoader<Car> listCar = ServiceLoader.load(Car.class);
        for ( Car c :listCar){
             c.color();
        }


    }
}
