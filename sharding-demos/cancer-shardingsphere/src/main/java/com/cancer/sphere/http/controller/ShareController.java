//package com.cancer.sphere.http.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.cancer.sphere.db.entity.Order;
//import com.cancer.sphere.db.entity.User;
//import com.cancer.sphere.db.mapper.OrderMapper;
//import com.cancer.sphere.db.mapper.UserMapper;
//import com.github.pagehelper.PageHelper;
//import io.shardingsphere.core.keygen.DefaultKeyGenerator;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import tk.mybatis.mapper.entity.Condition;
//
//import java.util.List;
//
///**
// * Created by zhoujian oppn 2018/6/29
// *
// * @Desc 类描述.
// */
//@Controller
//@Slf4j
//public class ShareController {
//
//
//
//   @Autowired
//   OrderMapper orderMapper;
//
//
//   @Autowired
//   UserMapper userMapper;
//
//
//
//   @RequestMapping("save")
//   @ResponseBody
//   public String save(){
//      DefaultKeyGenerator d = new DefaultKeyGenerator();
//      log.warn("{}", d.generateKey().longValue());
////      try {
////         City city = new City();
////         city.setId(111111111);
////         city.setName("1222");
////         cityMapper.save(city);
////      } catch (Throwable e) {
////         e.printStackTrace();
////      }
//      return "ok";
//   }
//
//   @RequestMapping("tt")
//   @ResponseBody
//   public String ss( @RequestParam("orderId") Long orderId , @RequestParam("userId") Integer userId ){
//
//      try {
//         Order o = new Order();
//         o.setOrderId(orderId);
//         o.setStatus("1");
//         o.setUserId(userId);
//         orderMapper.insert(o);
//
//
//      } catch (Throwable e) {
//         return "error";
//      }
//      return "ok";
//   }
//
//   @RequestMapping("addUser")
//   @ResponseBody
//   public String addUser(  ){
//
//      try {
//         DefaultKeyGenerator d = new DefaultKeyGenerator();
//         User u = new User();
//         u.setId(3L);
//         userMapper.insertSelective(u);
//
//      } catch (Throwable e) {
//         e.printStackTrace();
//         return "error";
//      }
//      return "ok";
//   }
//
//
//   @RequestMapping("page")
//   @ResponseBody
//   public String page( @RequestParam("pageIndex") Integer pageIndex ,@RequestParam("pageSize") Integer pageSize){
//
//         PageHelper.startPage(pageIndex,pageSize);
//
//      Condition condition=new Condition(Order.class);
//      //condition.createCriteria().andCondition("name like '%zhangsan%'");
//      condition.setOrderByClause("order_id asc");
//      List<Order> list = orderMapper.selectByExample(condition);
//      return JSON.toJSONString(list);
//   }
//}
