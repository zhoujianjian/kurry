package com.cancer.sphere.service.impl;

import com.cancer.sphere.db.entity.Order;
import com.cancer.sphere.db.mapper.OrderMapper;
import com.cancer.sphere.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

/**
 * @author: zhoujian
 * @Date: 2019/1/12 16:01
 * @Company: youanmi.
 * @Desc:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order  query ( String orderNo ){
        Condition condition = new Condition(Order.class);
        condition.createCriteria().andEqualTo("orderNo");
        return orderMapper.selectOneByExample(condition);
    }
}
