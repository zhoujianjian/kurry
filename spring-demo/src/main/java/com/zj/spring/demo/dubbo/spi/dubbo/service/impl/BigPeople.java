package com.zj.spring.demo.dubbo.spi.dubbo.service.impl;

import com.zj.spring.demo.dubbo.spi.dubbo.service.People;
import org.apache.dubbo.common.URL;

/**
 * @ClassName: BigPeople
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:34$
 * @Version: 1.0
 */
public class BigPeople implements People{
    @Override
    public void people() {
        System.out.println(" big people");
    }

    @Override
    public void testAdaptive(URL url) {

        System.out.println(" big people url  testAdaptive");
    }
}
