package com.td.pool;

import java.util.concurrent.Executors;

/**
 * @author: zhoujian
 * @Date: 2019/9/30 09:39
 * @Company: youanmi.
 * @Desc:
 */
public class PoolTest {

    public static void main(String[] args) {
        try {
            Executors.newCachedThreadPool();
            Executors.newFixedThreadPool(5);
            Executors.newSingleThreadExecutor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
