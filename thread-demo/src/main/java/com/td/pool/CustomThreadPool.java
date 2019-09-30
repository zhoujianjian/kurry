package com.td.pool;


import lombok.extern.slf4j.Slf4j;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zhoujian
 * @Date: 2019/9/30 10:24
 * @Company: youanmi.
 * @Desc:自定义线程池
 */
@Slf4j
public class CustomThreadPool {

    /**
     * Function:
     *
     * @author crossoverJie
     * Date: 2019-04-17 20:26
     * @since JDK 1.8
     */
    public interface Notify {

        /**
         * 回调
         */
        void notifyListen() ;
    }

    private final ReentrantLock lock = new ReentrantLock();


    /** 核心线程数量 */
    private volatile int coreSize;
    /** 最大线程数量 */
    private volatile int maxSize;
    /*** 线程需要被回收的时间 */
    private long keepAliveTime;
    private TimeUnit unit;
    /**  * 存放线程的阻塞队列 */
    private BlockingQueue<Runnable> workQueue;
    /**  * 存放线程池  */
    private volatile Set<Worker> workers;
    /**    * 是否关闭线程池标志  */
    private AtomicBoolean isShutDown = new AtomicBoolean(false);
    /** * 提交到线程池中的任务总数  */
    private AtomicInteger totalTask = new AtomicInteger();
    /**
     * 线程池任务全部执行完毕后的通知组件
     */
    private Object shutDownNotify = new Object();
    private Notify notify;



    public CustomThreadPool(int coreSize, int maxSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
    }


    public void execute( Runnable runnable ){
        if ( runnable == null ){
            throw new NullPointerException("runnable nullPointerException");
        }

        //计算提交线程数量
        totalTask.incrementAndGet();

        //工作线程 小于 核心线程数量
        if ( workers.size() < coreSize ){

        }
    }


    /**
     * 工作线程
     */
    private final class Worker extends Thread {

        private Runnable task;

        private Thread thread;
        /**
         * true --> 创建新的线程执行
         * false --> 从队列里获取线程执行
         */
        private boolean isNewTask;

        public Worker(Runnable task, boolean isNewTask) {
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        public void startTask() {
            thread.start();
        }

        public void close() {
            thread.interrupt();
        }

        @Override
        public void run() {

            Runnable task = null;

            if (isNewTask) {
                task = this.task;
            }

            boolean compile = true ;

            try {
                while ((task != null || (task = getTask()) != null)) {
                    try {
                        //执行任务
                        task.run();
                    } catch (Exception e) {
                        compile = false ;

                    } finally {
                        //任务执行完毕
                        task = null;
                        int number = totalTask.decrementAndGet();
                        //LOGGER.info("number={}",number);
                        if (number == 0) {
                            synchronized (shutDownNotify) {
                                shutDownNotify.notify();
                            }
                        }
                    }
                }

            } finally {
                //释放线程
                boolean remove = workers.remove(this);
                //LOGGER.info("remove={},size={}", remove, workers.size());

                if (!compile){
                    addWorker(null);
                }
                tryClose(true);
            }
        }
    }

    /**
     * 添加工作线
     * @param runnable
     */
    private void addWorker ( Runnable runnable){
        Worker worker = new Worker(runnable,true);
        worker.startTask();
        workers.add(worker);
    }

    /**
     * 从队列中获取任务
     *
     * @return
     */
    private Runnable getTask() {
        //关闭标识及任务是否全部完成
        if (isShutDown.get() && totalTask.get() == 0) {
            return null;
        }

        lock.lock();

        try {
            Runnable task = null;
            if (workers.size() > coreSize) {
                //大于核心线程数时需要用保活时间获取任务
                task = workQueue.poll(keepAliveTime, unit);
            } else {
                task = workQueue.take();
            }

            if (task != null) {
                return task;
            }
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }

        return null;
        //}
    }



    /**
     * 阻塞等到任务执行完毕
     */
    public void mainNotify() {
        synchronized (shutDownNotify) {
            while (totalTask.get() > 0) {
                try {
                    shutDownNotify.wait();
                    if (notify != null) {
                        notify.notifyListen();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    /**
     * 关闭线程池
     *
     * @param isTry true 尝试关闭      --> 会等待所有任务执行完毕
     *              false 立即关闭线程池--> 任务有丢失的可能
     */
    private void tryClose(boolean isTry) {
        if (!isTry) {
            closeAllTask();
        } else {
            if (isShutDown.get() && totalTask.get() == 0) {
                closeAllTask();
            }
        }

    }

    /**
     * 关闭所有任务
     */
    private void closeAllTask() {
        for (Worker worker : workers) {
            //LOGGER.info("开始关闭");
            worker.close();
        }
    }

    /**
     * 获取工作线程数量
     *
     * @return
     */
    public int getWorkerCount() {
        return workers.size();
    }


    /**
     * 内部存放工作线程容器，并发安全。
     *
     * @param <T>
     */
    private final class ConcurrentHashSet<T> extends AbstractSet<T> {

        private ConcurrentHashMap<T, Object> map = new ConcurrentHashMap<>();
        private final Object PRESENT = new Object();

        private AtomicInteger count = new AtomicInteger();

        @Override
        public Iterator<T> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public boolean add(T t) {
            count.incrementAndGet();
            return map.put(t, PRESENT) == null;
        }

        @Override
        public boolean remove(Object o) {
            count.decrementAndGet();
            return map.remove(o) == PRESENT;
        }

        @Override
        public int size() {
            return count.get();
        }
    }


}
