package com.data.structure.lagou;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ArrayMaxNumTest
 * @Description: 查询数组中出现次数最多
 * @Author: zhoujian
 * @Date: 2020/6/12$ 11:44$
 * @Version: 1.0
 */
public class ArrayMaxNumTest {

    public static void main(String[] args) {
       List<Integer> integerList = Arrays.asList(1,2,3,4,4,5,5,6,6,6,6,7,7);
       Map<Integer,Integer> map = new HashMap<Integer,Integer>();
       int max=0;
       int maxNum = 0;
       for ( Integer i : integerList ){
           if ( map.containsKey(i)){
               map.put(i, map.get(i)+1);
           }else {
               map.put(i, 1);
           }
           int temp = map.get(i);
           if (temp > max){
                max=temp;
                maxNum = i;
           }
       }
        System.out.println(maxNum);
       System.out.println(map);

    }
}