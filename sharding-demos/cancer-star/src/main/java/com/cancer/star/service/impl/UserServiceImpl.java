package com.cancer.star.service.impl;

import com.cancer.star.dao.UserDao;
import com.cancer.star.db.entity.User;
import com.cancer.star.http.param.req.ReqLogin;
import com.cancer.star.constants.StarConstants;
import com.cancer.star.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhoujian on 2018/7/5
 *
 * @Desc 类描述.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User getUser( String mobile ){
        return userDao.query(mobile);
    }


}
