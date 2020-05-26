package com.data.structure.sort;

import java.util.Arrays;

/**
 * @ClassName: MaoPao
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/4/22$ 17:06$
 * @Version: 1.0
 */
public class MaoPao {

    public static void main(String[] args) {
        int[] array =new int[]{6,5,8,2,3,9,4,10};
        //MaoPao.mao1(array);
        MaoPao.mao2(array);
    }

    private static void mao2( int[] array ) {
        int length = array.length;
        for (int i = 0; i < length ; i++) {
             for (int j = 0; j < length -i- 1; j++) {
                 if ( array[j+1] > array[j] ){
                     int temp = array[j];
                     array[j] = array[j+1];
                     array[j+1] = temp;
                 }
             }
         }

        System.out.println(Arrays.toString(array));
    }

    private static void mao1( int[] array ) {
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array.length-1; i++) {
                if ( array[i+1] > array[i] ){
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }
}
