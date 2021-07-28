package com.data.structure.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhaoTarget {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        System.out.println(ZhaoTarget.getTarget(list,5));

    }


    public static int getTarget(List<Integer> list ,int target){
        int idx = 0;

        int begin = 0;
        int end = list.size()-1;
        int center = (begin+end)/2;

        while (true){
            if ( target == list.get(center)){
                idx = center;
                break;
            }else if ( target < list.get(center)){
                end = center-1;
            }else {
                begin = center+1;
            }
            center = (begin+end)/2;
        }
        return idx;
    }





}
