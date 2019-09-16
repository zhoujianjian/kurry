package com.cancer.sphere.service;

import com.cancer.sphere.db.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zhoujian
 * @Date: 2019/1/12 17:10
 * @Company: youanmi.
 * @Desc:
 */
@Service
public class AServiceImpl {

    @Autowired
    OrderService orderService;

    OrderMapper orderMapper;


    public void handle( ){
        String orderNo ="11111";
        orderService.query(orderNo);
        System.out.println("业务处理");
    }

}
