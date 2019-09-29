package com.design.单例;

/**
 * @author: zhoujian
 * @Date: 2019/9/23 11:01
 * @Company: youanmi.
 * @Desc:
 */
public enum  B {
    SINGLE;
    private  A a = null;
    private B(){
        a = new A();
    }
    public A getA(){
        return a;
    }
}
class A {}


class C{

    public static void main(String[] args) {
        try {
            A a = B.SINGLE.getA();
            A a1 = B.SINGLE.getA();
            System.out.println(a == a1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}