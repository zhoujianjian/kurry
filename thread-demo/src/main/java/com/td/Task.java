package com.td;

/**
 * @author: zhoujian
 * @Date: 2019/4/18 16:50
 * @Company: youanmi.
 * @Desc:
 */
public class Task {



        public static void main(String[] args) {
            Washroom washroom = new Washroom();
            new Thread(new RepairTask(washroom), "REPAIR-THREAD").start();

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            new Thread(new ShitTask(washroom, "狗哥"), "BROTHER-DOG-THREAD").start();
            new Thread(new ShitTask(washroom, "猫爷"), "GRANDPA-CAT-THREAD").start();
            new Thread(new ShitTask(washroom, "王尼妹"), "WANG-NI-MEI-THREAD").start();
        }



}
