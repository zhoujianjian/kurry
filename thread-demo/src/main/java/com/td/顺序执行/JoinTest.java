package com.td.顺序执行;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author: zhoujian
 * @Date: 2019/9/18 10:02
 * @Company: youanmi.
 * @Desc:
 */
 public class JoinTest {


     @Test
     public void testJoin(){
         Thread thread1 = new Thread(new Runnable() {
             public void run() {
                 System.out.println("1");
             }
         });


         Thread thread2 = new Thread(new Runnable() {
             public void run() {
                 System.out.println("2");
             }
         });


         try {
             thread1.start();
            thread1.join();
             thread2.start();
            thread2.join();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }


     @Test
     public void testJoin2(){
         final Thread thread3 = new Thread(new Runnable() {
             public void run() {
                 System.out.println("3");
             }
         });


        final Thread thread4 = new Thread(new Runnable() {
             public void run() {
                 try {
                     thread3.join();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println("4");
             }
         });

         thread3.start();
         thread4.start();
     }


     @Test
    public   void testCountDownLatch( ) {
         /**
          * 用于判断线程一是否执行，倒计时设置为1，执行后减1
          */
         final CountDownLatch c1 = new CountDownLatch(1);

          Thread thread3 = new Thread(new Runnable() {

              @Override
              public void run() {
                 System.out.println("5");
              }
         });


          Thread thread4 = new Thread(new Runnable() {
              @Override
              public void run() {

                 System.out.println("6");
             }
         });

         thread3.start();
         thread4.start();
         System.out.println("over");

     }






}
