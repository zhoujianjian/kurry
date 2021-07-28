package com.data.structure.sort;

public class Feibolaqieshulie {


    public static void main(String[] args) {
        // 1 1 2 3 5 8 13
        for ( int i =1;i<20;i++){
            System.out.println(Feibolaqieshulie.fbnq(i));
        }
    }


    public static int fbnq(int index){
        if ( index == 1 || index == 2){
            return 1;
        }else {
            return fbnq(index-1)+fbnq(index-2);
        }
    }

}
