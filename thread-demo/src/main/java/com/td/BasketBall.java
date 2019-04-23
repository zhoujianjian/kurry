package com.td;

import org.junit.Test;

/**
 * @author: zhoujian
 * @Date: 2019/4/19 14:18
 * @Company: youanmi.
 * @Desc:
 */
public class BasketBall {
    /**
     * 被别人抢走了
     */
    private Object lock = new Object();

    //
    private volatile boolean  flag = false ;

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class Player implements Runnable{

    private String name;

    private BasketBall basketBall;

    public Player( String name ,BasketBall basketBall){
        this.name = name;
        this.basketBall = basketBall;
    }

    public void run() {
        synchronized ( basketBall.getLock() ){

            while ( !basketBall.isFlag() ){
                try {
                    basketBall.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

             while ( basketBall.isFlag() ){
                System.out.println( name + "抢住了篮板,正在运球" );
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                basketBall.setFlag(false);
                System.out.println( name + "投篮不中，篮板球！" + basketBall.isFlag() );
            }

        }

    }

}


class Caipan implements Runnable{

    private BasketBall basketBall;

    public Caipan(  BasketBall basketBall){
         this.basketBall = basketBall;
    }
    public void run() {

            synchronized ( basketBall.getLock() ){
                System.out.println( "比赛开始，开始跳球" );
                try {
                    Thread.sleep(100);
                    System.out.println(   "争夺球权" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                basketBall.setFlag(true);
                basketBall.getLock().notifyAll();
             }
        }



}



class Result {



    @Test
    public void test1(){

        BasketBall basketBall = new BasketBall();

        Player kb = new Player("科比",basketBall);
        Player lbj = new Player("詹姆斯",basketBall);
        Thread t1 = new Thread(kb);
        Thread t2 = new Thread(lbj);

        t1.start();
        t2.start();

    }





    public static void main(String[] args) {

        BasketBall basketBall = new BasketBall();

        Player kb = new Player("科比",basketBall);
        Player lbj = new Player("詹姆斯",basketBall);
        Caipan c = new Caipan(basketBall);

        Thread t1 = new Thread(kb);
        Thread t2 = new Thread(lbj);
        Thread t0 = new Thread(c);


        t0.start();
        t1.start();
        t2.start();

    }


}