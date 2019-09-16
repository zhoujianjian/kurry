package com.td.t;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhoujian
 * @Date: 2019/4/23 16:36
 * @Company: youanmi.
 * @Desc:
 */
public class AutomicTest implements Runnable {


    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    public void run() {

         System.out.println(Thread.currentThread().getName()+"  " + atomicInteger.incrementAndGet());
    }





    public static void main(String[] args) {


          ThreadGroup accountThawThreadGroup = new ThreadGroup("AccountThawJobTG");
          System.out.println(accountThawThreadGroup.activeCount());
        try {
            new Thread(accountThawThreadGroup,new AutomicTest()).start();
            new Thread(accountThawThreadGroup,new AutomicTest()).start();
            new Thread(accountThawThreadGroup,new AutomicTest()).start();

            System.out.println(accountThawThreadGroup.activeCount());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
