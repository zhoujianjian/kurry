package com.zj.spring.demo.spring.test;

import org.springframework.stereotype.Component;

@Component("testA")
public class TestA {

    public TestA() {

        System.out.println("TestA被实例化了");
    }
}
