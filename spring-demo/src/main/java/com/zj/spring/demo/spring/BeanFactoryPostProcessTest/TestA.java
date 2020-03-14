package com.zj.spring.demo.spring.BeanFactoryPostProcessTest;

import org.springframework.stereotype.Component;

@Component("testA")
public class TestA {

    public TestA() {

        System.out.println("111111111111111111111");
    }
}
