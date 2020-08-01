package com.td.生产者消费者;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: WaitTest
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/8$ 19:25$
 * @Version: 1.0
 */
public class WaitTest {

    public static void main(String[] args) {
        Storage storage = new Storage(new ArrayList<Integer>());
        Thread p = new Thread(new P(storage));
        Thread c = new Thread(new C(storage));
         c.start();
         p.start();

    }
}

class P implements Runnable{

    private Storage storage;

    public P(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        synchronized (storage){
            while (true){
                for (int i = 0; i < 10; i++) {
                    storage.getList().add(i);
                    System.out.println("生产"+i);
                }
                try {
                     storage.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }
}



class C implements Runnable{

    private Storage storage;

    public C(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        synchronized (storage){
            int i = 0;
            while (storage.getList().isEmpty()){
                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!storage.getList().isEmpty()){
                int size = storage.getList().size();
                for (int j = size - 1; j >= 0; j--) {
                    storage.getList().remove(j);
                    System.out.println("线程"+Thread.currentThread().getName()+"成功消费"+j+"条消息");
                }


            }
        }
    }
}



class Storage  {
    private List<Integer> list;

    public Storage(List<Integer> list) {
        this.list = new ArrayList<Integer>();
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}