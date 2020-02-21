package com.zj.spring.demo.BeanFactoryPostProcessTest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;


/***
 * 决定生成bean 的方式
 * class 决定  spring 要实例化 那个 class
 * autowire_Model   自动注入的方式
 *
 * 1 autowire_no(0)： 默认装配模式， 目前非xml配置都是使用这种方式，然后程序员使用注解手动注入
 *
 * 2 autowire_name: 通过set方法，并且 set方法的名称需要和bean的name一致     byName
 *
 * 3 autowire_type: 通过set方法,并且再根据bean的类型，注入属性，是通过类型配置  byType
 *
 * 4 autowire_construcor: 通过构造器注入
 *
 *  construcorArgumentValue   指定用那个构造器
 */
@Component
public class BeanFactoryPostTest implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

       BeanDefinition testABeanDefinition = configurableListableBeanFactory.getBeanDefinition("testA");
       //testABeanDefinition.setBeanClassName("com.zj.spring.demo.BeanFactoryPostProcessTest.TestB");
       System.out.println(testABeanDefinition.getBeanClassName());


        RootBeanDefinition rootBeanDefinition = (RootBeanDefinition)configurableListableBeanFactory.getBeanDefinition("testA");
        rootBeanDefinition.setAutowireMode(1);

        GenericBeanDefinition genericBeanDefinition =(GenericBeanDefinition)configurableListableBeanFactory.getBeanDefinition("testA");
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        //constructorArgumentValues.addArgumentValues();
        genericBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);
    }


}
