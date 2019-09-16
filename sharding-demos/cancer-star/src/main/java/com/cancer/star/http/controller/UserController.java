package com.cancer.star.http.controller;

import com.cancer.commons.api.APIResponse;
import com.cancer.commons.api.enums.ApiResponseEnum;
import com.cancer.commons.plugins.validation.base.Update;
import com.cancer.star.db.entity.User;
import com.cancer.star.http.param.req.ReqLogin;
import com.cancer.star.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by zhoujian on 2018/7/5
 *
 * @Desc 类描述.
 */
@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public APIResponse login ( @RequestBody ReqLogin req){
        String mobile = req.getMobile();
        User user = userService.getUser(mobile);
        if ( user == null ){
            return APIResponse.returnFail(ApiResponseEnum.USER_NOT_EXISTENT);
        }

        if ( user.getPwd().equals(req.getPwdOrCode()) ){
            return APIResponse.returnFail(ApiResponseEnum.USER_NOT_EXISTENT);
        }

        return APIResponse.returnSuccess( user );
    }


    @RequestMapping("update")
    public APIResponse  update (@Validated( Update.class) @RequestBody ReqLogin reqLogin){
        return APIResponse.returnSuccess();
    }
}
