package com.zj.spring.demo.collecList;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName: BlockQTest
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/8/6$ 13:52$
 * @Version: 1.0
 */
public class BlockQTest {

    public void takerOfferElement() throws Exception{
        BlockingQueue blockingQueue = new ArrayBlockingQueue(12);

        Thread.sleep(1111L);
        blockingQueue.offer(1);
        Thread.sleep(1111L);
//        blockingQueue.offer(2);
//        blockingQueue.offer(3);
       blockingQueue.offer(4);



        while (!blockingQueue.isEmpty()){

             //System.out.println(blockingQueue.peek());

          System.out.println(blockingQueue.poll());

        }

    }


    public void addRemoveElement(){
        BlockingQueue blockingQueue = new ArrayBlockingQueue(12);

        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        blockingQueue.add(4);


        while (!blockingQueue.isEmpty()){
            System.out.println(blockingQueue.element());
            System.out.println(blockingQueue.remove(blockingQueue.element()));
            System.out.println("blockingQueue.size():"+blockingQueue.size());
            //  blockingQueue.remove(1);
            //  System.out.println(blockingQueue.take());
        }

    }

    public static void main(String[] args)  throws Exception{
        BlockQTest b= new BlockQTest();
        b.takerOfferElement();
     }
}
