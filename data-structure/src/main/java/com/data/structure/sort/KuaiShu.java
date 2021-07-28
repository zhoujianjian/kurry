package com.data.structure.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: kuaishu
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/4/23$ 15:15$
 * @Version: 1.0
 */
public class KuaiShu {


    public static void main(String[] args) {
        int[] array =new int[]{6,5,8,2,3,9,4,10};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }


    public static void quickSort( int[] array ,int start ,int end ){
        if ( start>= end){
          return;
        }
        //基准
        int base = array[start];
        //左边
        int low = start;
        int high = end;

        while (low < high ){
            while (low < high && base<=array[high]){
                high --;
            }
            array[low] = array[high];

            //最低的位置比 基准数 大
            while (low < high && base >= array[low]){
                low ++;
            }
            array[high] = array[low];
        }
        array[low]=high;

        quickSort(array,start,low);
        quickSort(array,low+1,end);

    }




}
