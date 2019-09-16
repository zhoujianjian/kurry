package com.design.单例;

/**
 * @author: zhoujian
 * @Date: 2019/9/16 14:36
 * @Company: youanmi.
 * @Desc:静态内部类
 */
public class SingleA {
    private SingleA(){
        if (Single.single != null) {
            throw new IllegalStateException();
        }
    }
    private static class Single {
        private static Single single = new Single();
    }

    public static Single getSingle(){
        return Single.single;
    }
}
