package com.td.t;

/**
 * @author: zhoujian
 * @Date: 2019/4/18 16:48
 * @Company: youanmi.
 * @Desc:
 */
public class ShitTask  implements Runnable {

    private Washroom washroom;

    private String name;

    public ShitTask(Washroom washroom, String name) {
        this.washroom = washroom;
        this.name = name;

    }

    public void run() {

        synchronized (washroom.getLock()) {
            System.out.println(name + " 获取了厕所的锁");
            while (!washroom.isAvailable()) {
                // 一直等
            }
            System.out.println(name + " 上完了厕所");

        }

        }
}
