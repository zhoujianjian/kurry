package com.zj.spring.demo;

import com.zj.spring.demo.BeanFactoryPostProcessTest.TestA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
 public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        //TestA testA = (TestA)applicationContext.getBean("testA");


    }

}
