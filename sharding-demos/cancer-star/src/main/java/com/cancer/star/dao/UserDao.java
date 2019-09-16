package com.cancer.star.dao;

import com.cancer.star.db.entity.User;

/**
 * Created by zhoujian on 2018/7/5
 *
 * @Desc 类描述.
 */
public interface UserDao {

     User query( String mobile );

     User query( Integer userId );

     User query ( String mobile , String password );

     void insert ( User insert );


}
