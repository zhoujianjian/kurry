package com.cancer.sphere.service;

import com.cancer.sphere.db.entity.Order;

public interface OrderService{


    Order query(String orderNo);
}