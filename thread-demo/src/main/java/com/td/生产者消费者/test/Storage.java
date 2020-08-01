package com.td.生产者消费者.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: Storage
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/9$ 11:22$
 * @Version: 1.0
 */
public class Storage {
    private List<String> storage;//生产者和消费者共享的仓库

    public Storage() {
        storage = new ArrayList<String>();
    }
    public List<String> getStorage() {
        return storage;
    }
    public void setStorage(List<String> storage) {
        this.storage = storage;
    }

}



class Consumer extends Thread {

    private List<String> storage;//仓库

    public Consumer(List<String> storage) {
        this.storage = storage;
    }

    public void run() {
        while (true) {
            synchronized (storage) {
                //消费者去仓库拿消息的时候，如果发现仓库数据为空，则等待
                if (storage.isEmpty()) {
                    try {
                        storage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int size = storage.size();
                for (int i = size - 1; i >= 0; i--) {
                    storage.remove(i);
                }
                System.out.println("线程" + this.getName() + "成功消费" + size + "条消息");
            }
        }
    }
}


class Producer extends Thread{

    private List<String> storage;//生产者仓库
    public Producer(List<String> storage) {
        this.storage = storage;
    }
    public void run(){
        //生产者每隔1s生产1~100消息
        long oldTime = System.currentTimeMillis();
        while(true){
            synchronized(storage){
                if (System.currentTimeMillis() - oldTime >= 1000) {
                    oldTime = System.currentTimeMillis();
                    int size = 10;
                    for (int i = 0; i < size; i++) {
                        String msg = UUID.randomUUID().toString();
                        storage.add(msg);
                    }
                    System.out.println("线程"+this.getName()+"生产消息"+size+"条");
                    storage.notifyAll();
                }
            }
        }
    }
}


class R{

    public static void main(String[] args) {
        Storage storage = new Storage();
        Producer producer = new Producer(storage.getStorage());
        Consumer consumer = new Consumer(storage.getStorage());
        Consumer consumer1 = new Consumer(storage.getStorage());
        producer.start();
        consumer.start();
        consumer1.start();
    }

}

