package com.test.controller;

import com.test.controller.po.Zhou;
import com.test.controller.repository.ZhouRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/24$ 13:47$
 * @Version: 1.0
 */
@RestController
public class TestController {

    @Autowired
    private ZhouRepository zhouRepository;

    @RequestMapping("/test")
    public String get(@RequestBody Zhou z){
        zhouRepository.save(z);
        return "ok";
    }
}
