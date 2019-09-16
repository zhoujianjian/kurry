package com.cancer.star.http.controller;

import com.cancer.commons.api.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhoujian on 2018/7/5
 *
 * @Desc 类描述.
 */
@RequestMapping("/api/appointment/")
@RestController
public class AppointmentController {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("test")
    public APIResponse test(){

        redisTemplate.opsForValue().set("zj","zhoujian");

        System.out.println(redisTemplate.opsForValue().get("zj"));


        return APIResponse.returnSuccess();
    }
}
