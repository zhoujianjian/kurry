package com.zj.spring.demo.dubbo.spi.dubbo.service.impl;

import com.zj.spring.demo.dubbo.spi.dubbo.service.People;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

/**
 * @ClassName: LittlePeople
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:35$
 * @Version: 1.0
 */
public class LittlePeople implements People {
    @Override
    public void people() {
        System.out.println(" little people");
    }

    @Override
    @Adaptive
    public void testAdaptive(URL url) {
        System.out.println(" big people url  testAdaptive");
    }
}
