package com.data.structure.sort;

public class Hanluota {


    public static void main(String[] args) {
        hanoi(2,'a','b','c');
    }


    public static void hanoi ( int n ,char form  ,char center ,char to){
        if ( n==1){
            System.out.println("第一个盘子从"+form +"移动到"+to) ;
        }else {
            //移动 个盘子 到 中间
            hanoi(n-1,form,to,center);


            hanoi(n-1,to,center,to);

        }
    }
}
