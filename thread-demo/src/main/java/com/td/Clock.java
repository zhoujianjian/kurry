package com.td;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author: zhoujian
 * @Date: 2019/4/18 17:21
 * @Company: youanmi.
 * @Desc 让线程串行的方式 1 sleep 2 join 3 countdownlanch
 */
 public class Clock {

  static   CountDownLatch countDownLatch = new CountDownLatch(1);

    private String currentTime;

    /**
     *获取当前时间
     */
    class TimeThread extends Thread{
        @Override
        public void run() {
            String pattern = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            currentTime = sdf.format(new Date());
            //countDownLatch.countDown();

        }
    }

    /**
     * 显示时间
     */
    class ShowThread extends Thread{
        @Override
        public void run() {
//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("现在的时间是:"+currentTime);
        }
    }


    public static void main(String[] args) {
        Clock clock = new Clock();
        TimeThread timeThread = clock.new TimeThread();
        ShowThread showThread = clock.new ShowThread();
//        try {
//            timeThread.start();
//            timeThread.join();
//
//            showThread.start();
//            showThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



        timeThread.start();
        try {
            countDownLatch.await();
            countDownLatch.getCount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showThread.start();

    }



    @Test
    public  void  sleep1(){
        CountDownLatch c = new CountDownLatch(3);
        c.countDown();
        System.out.println(c.getCount());
        c.countDown();
        System.out.println(c.getCount());
        c.countDown();
        System.out.println(c.getCount());
    }

}
