package com.zj.spring.demo.dubbo.spi.dubbo.service.impl.wrapper;

import com.zj.spring.demo.dubbo.spi.dubbo.service.People;
import org.apache.dubbo.common.URL;

/**
 * @ClassName: BigPeopleWrapper
 * @Description: 描述
 * @Author: wangpan
 * @Date: 2020/3/14$ 11:41$
 * @Version: 1.0
 */
public class BigPeopleWrapper implements People {

    private People people;

    public BigPeopleWrapper( People people){
        this.people = people;
    }

    @Override
    public void people() {
        System.out.println(" wrapper  begin ");
        people.people();
        System.out.println(" wrapper  end ");
    }


    @Override
    public void testAdaptive(URL url) {
        System.out.println(" wrapper testAdaptive  begin ");
        people.people();
        System.out.println(" wrapper testAdaptive  end ");
    }
}
