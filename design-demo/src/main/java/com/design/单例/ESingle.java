package com.design.单例;

/**
 * @author: zhoujian
 * @Date: 2019/9/18 16:28
 * @Company: youanmi.
 * @Desc:
 */
public enum ESingle {

    INSTANCE;

    private ESingle  getESingle(){
        return ESingle.INSTANCE;
    }

}
