package com.zj.spring.demo.BeanFactoryPostProcessTest;

import org.springframework.stereotype.Component;

@Component("testA")
public class TestA {

    public TestA() {

        System.out.println("111111111111111111111");
    }
}
