package com.zj.spring.demo.dubbo.spi.dubbo.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @ClassName: Pelople
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:33$
 * @Version: 1.0
 */
@SPI("little")
public interface People {

    void people();

    @Adaptive
    void testAdaptive(URL url);
}
