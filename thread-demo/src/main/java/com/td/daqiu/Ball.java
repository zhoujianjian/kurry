package com.td.daqiu;

/**
 * @ClassName: Ball
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/7/8$ 14:40$
 * @Version: 1.0
 */
public class Ball {

    private Object lock = new Object();

    private volatile boolean flag = false;

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

}

class Result{

    public static void main(String[] args) {
        Ball b = new Ball();





        Thread t1 = new Thread( new CaiPan(b),"裁判");
        Thread t2 = new Thread( new Player(b),"科比");
        Thread t3 = new Thread( new Player(b),"詹姆斯");
        t1.start();t2.start();t3.start();


    }
}



class CaiPan implements Runnable{
    private Ball ball;
    public CaiPan(Ball ball) {
        this.ball = ball;
    }
    @Override
    public void run() {
        synchronized (ball.getLock()){
            System.out.println(Thread.currentThread().getName()+"拿到球，跳球开始比赛");
            try {
                Thread.sleep(1000);
                ball.setFlag(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ball.getLock().notifyAll();
        }
    }
}

class Player implements Runnable{
    private Ball ball;
    public Player(Ball ball) {
        this.ball = ball;
    }
    @Override
    public void run() {
        synchronized (ball.getLock()){
            while (ball.isFlag()){
                System.out.println(Thread.currentThread().getName()+"抢到了篮球");
                try {
                    ball.setFlag(false);
                    Thread.sleep(1000);
                    ball.setFlag(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!ball.isFlag()){
                System.out.println(Thread.currentThread().getName()+"比赛还没开始");
                try {
                    ball.getLock().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}