package com.cancer.star.service;

import com.cancer.star.db.entity.User;

/**
 * Created by zhoujian on 2018/7/5
 *
 * @Desc 类描述.
 */
public interface UserService {

     User getUser( String mobile );
}
