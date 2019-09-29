package com.design.单例;

/**
 * @author: zhoujian
 * @Date: 2019/9/18 16:08
 * @Company: youanmi.
 * @Desc:线程安全，防止序列化，反射破坏单例模式
 */
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance(){
        return EnumSingle.INSTANCE;
    }


    public void doSomething() {
        System.out.println("doSomething");
    }


    public static void main(String[] args) {
        EnumSingle enumSingle =  EnumSingle.INSTANCE;
        EnumSingle enumSingle2 =  EnumSingle.INSTANCE;

        System.out.println(enumSingle==enumSingle2);
    }

}
