package com.cancer.share.controller;

import com.cancer.share.db.entity.Order;
import com.cancer.share.db.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by zhoujian oppn 2018/6/29
 *
 * @Desc 类描述.
 */
@Controller
public class ShareController {


   @Autowired
   OrderMapper orderMapper;


   @RequestMapping("tt")
   @ResponseBody
   public String ss(){

      try {
         Order o = new Order();
        o.setOrderId(22222222L);
         o.setStatus("1");
         o.setUserId(10000);
         orderMapper.insert(o);
      } catch (Throwable e) {
         e.printStackTrace();
      }
      return "ok";
   }


   @RequestMapping("page")
   @ResponseBody
   public String page( @RequestParam("pageIndex") Integer pageIndex ,@RequestParam("pageSize") Integer pageSize){

       PageHelper.startPage(pageIndex,pageSize);

      Condition condition=new Condition(Order.class);
      //condition.createCriteria().andCondition("name like '%zhangsan%'");
      condition.setOrderByClause("order_id asc");
     // List<Order> list = orderMapper.selectByExample(condition);
      List<Order> list1 = orderMapper.selectAll();



         return null;
   }
}
