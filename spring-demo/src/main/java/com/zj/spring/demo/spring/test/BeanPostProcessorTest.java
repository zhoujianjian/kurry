package com.zj.spring.demo.spring.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Bean后置处理器
 */
@Component
public class BeanPostProcessorTest implements BeanPostProcessor {


    /***
     * 其中postProcessBeforeInitialization方法会在每一个bean对象的初始化方法调用之前回调；
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    /**
     * postProcessAfterInitialization方法会在每个bean对象的初始化方法调用之后被回调。
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
