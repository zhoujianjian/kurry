package com.td.t;


/**
 * @author: zhoujian
 * @Date: 2019/4/18 16:21
 * @Company: youanmi.
 * @Desc:
 */
public class Washroom {

    //表示厕所是否是可用的状态
    private volatile boolean isAvailable = false;

    //厕所门的锁
    private Object lock = new Object();

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public Object getLock() {
        return lock;
    }






}
