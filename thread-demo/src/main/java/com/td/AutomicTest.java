package com.td;

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

         System.out.println(Thread.currentThread().getName() + atomicInteger.incrementAndGet());
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        AutomicTest a = new AutomicTest();
        for ( int i = 0 ;i<5;i++){
            executorService.execute(a);
        }
        executorService.shutdown();
    }

}
