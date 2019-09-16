package com.td.t;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhoujian
 * @Date: 2019/4/23 15:25
 * @Company: youanmi.
 * @Desc:
 */
public class Water {

   private Object lock = new Object();


   /** 有水 没水false **/
   private volatile boolean flag = false;

   private AtomicInteger atomicInteger = new AtomicInteger(0);

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }
}

 class People implements Runnable{

    private Water water;

    private String name;

    public People ( Water water ,String name ){
         this.water =  water;
         this.name = name;
    }

    public void run() {
        synchronized ( water.getLock() ){
            System.out.println(name + "来接水了" + water.getAtomicInteger().get());
            while ( water.getAtomicInteger().get() == 0 ){
                try {
                    System.out.println( "没水了");
                    water.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("接水成功");
        }
    }
}


class SongWater implements Runnable {

    private Water water;

    public SongWater ( Water water ){
        this.water =  water;
     }

    public void run() {

        synchronized ( water.getLock() ) {
            System.out.println(  "有水吗？？？？？" + water.getAtomicInteger().get());

            try {
                System.out.println("加水中");
                Thread.sleep(500L);
                System.out.println("加水end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            water.getAtomicInteger().incrementAndGet();
            System.out.println(  "加完水" + water.getAtomicInteger().get());

            water.getLock().notify();
        }

    }


}

class Results {


    public static void main(String[] args) {

        Water w = new Water();

        People p = new People(w,"wqq");
        SongWater s = new SongWater(w);

        Thread tp = new Thread(p);
        Thread ts = new Thread(s);

        tp.start();
        ts.start();

    }
}
