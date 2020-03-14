package com.zj.spring.demo.dubbo.spi.dubbo.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;


/**
 * @ClassName: DubboSpiTest
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:37$
 * @Version: 1.0
 */
public class DubboSpiTest {

    public static void main(String[] args) {
        ExtensionLoader extensionLoader =ExtensionLoader.getExtensionLoader(People.class);
        //ioc 根据key 获取
//        People key = (People) extensionLoader.getExtension("big");
//        key.people();
//
//        // 默认 根据 @SPI 主键上 默认值
//        People defualt = (People) extensionLoader.getDefaultExtension();
//        defualt.people();


        try {
            URL url = URL.valueOf("dubbo://192.168.0.101:20880?fruit.granter=little");

            People adptivePeople = (People)  extensionLoader.getAdaptiveExtension();
            adptivePeople.testAdaptive(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
