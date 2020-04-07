package com.zj.spring.demo.dubbo.spi.dubbo.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: DubboSpiTest
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:37$
 * @Version: 1.0
 */
public class DubboSpiTest {

  static   ExtensionLoader extensionLoader =ExtensionLoader.getExtensionLoader(People.class);

   public static void byKey(){
       //ioc 根据key 获取
        People key = (People) extensionLoader.getExtension("big");
        key.people();
   }


    public static void bySpiDefaultKey(){
       // 默认 根据 @SPI 主键上 默认值
         People defualt = (People) extensionLoader.getDefaultExtension();
         defualt.people();
    }

    public static void byUrl(){
        //根据 url 自适应  代理类
        Map<String,String> mp = new HashMap<>();
        mp.put("people","big");
        URL url = new URL("12311","23423",33,mp);
        People adptivePeople = (People)  extensionLoader.getAdaptiveExtension();
        adptivePeople.testAdaptive(url);

    }

    public static void main(String[] args) {
        DubboSpiTest.bySpiDefaultKey();
    }
}
