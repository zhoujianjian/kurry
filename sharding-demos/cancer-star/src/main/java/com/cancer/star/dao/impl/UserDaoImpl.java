package com.cancer.star.dao.impl;

import com.cancer.star.dao.UserDao;
import com.cancer.star.db.entity.User;
import com.cancer.star.db.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by zhoujian on 2018/7/5
 *
 * @Desc 类描述.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    //@Cacheable(value= StarConstants.EhcacheKeys.USERS, key="'user_mobile'+ #mobile")
    public User query(   String mobile) {
        if (StringUtils.isNotBlank((mobile))) {
            User find = new User();
            find.setMobile(mobile);

            return userMapper.selectOne(find);
        }
        return null;
    }

    @Override
    public User query( Integer userId ) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public User query(   String mobile,   String password ) {
        User find = new User();
        find.setMobile(mobile);
        find.setPwd(password);
        return userMapper.selectOne(find);
    }

    @Override
    public void insert ( User insert ){
        userMapper.insertSelective(insert);
    }

}
