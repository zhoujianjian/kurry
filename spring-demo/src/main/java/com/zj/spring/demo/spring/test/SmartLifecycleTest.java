package com.zj.spring.demo.spring.test;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SmartLifecycleTest
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/4/4$ 20:27$
 * @Version: 1.0   bean实例初始化之后   spring 容器启动之前 执行  start方法
 */
@Component
public class SmartLifecycleTest implements SmartLifecycle {
    @Override
    public void start() {
        System.out.println("SmartLifecycle  start");
    }

    @Override
    public void stop() {
        System.out.println("SmartLifecycle  stop");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
