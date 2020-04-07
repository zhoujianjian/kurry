package com.zj.spring.demo;

import com.zj.spring.demo.spring.test.importsss.selector.ImportSelectortTest;
import com.zj.spring.demo.spring.test.TestA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
//SpringBootApplication
@Import(ImportSelectortTest.class)
 public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        TestA testA = (TestA)applicationContext.getBean("testA");

    }

}
