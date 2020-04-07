package com.zj.spring.demo.dubbo.service;

import org.apache.dubbo.rpc.model.ApplicationInitListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DubboServiceTest
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 10:56$
 * @Version: 1.0
 */
@Component
public class DubboServiceTest implements ApplicationContextAware,BeanNameAware,InitializingBean ,ApplicationListener,DisposableBean {

    private DubboServiceTest service;

    public DubboServiceTest() {

        System.out.println("DubboServiceTest 实例化");
        this.service = null;
    }

    @Override
    public void setBeanName(String s) {
     System.out.println("BeanNameAware  setBeanName");
    }

    @Override
    public void setApplicationContext(ApplicationContext setApplicationContext) throws BeansException {
        System.out.println("ApplicationContextAware  applicationContext");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean   afterPropertiesSet");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("ApplicationListener  onApplicationEvent");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean   afterPropertiesSet");
    }
}
