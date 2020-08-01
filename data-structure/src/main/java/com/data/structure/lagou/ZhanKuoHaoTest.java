package com.data.structure.lagou;

import java.util.Arrays;
import java.util.Stack;

/**
 * @ClassName: ZhanKuoHaoTest
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/6/28$ 17:17$
 * @Version: 1.0
 */
public class ZhanKuoHaoTest {


    public static void main(String[] args) {

        String s = "()[]{}";
        System.out.println(ZhanKuoHaoTest.t(s));

    }

    public  static boolean  t(String str){
        boolean flag = true;
        char[] arr = str.toCharArray();
        int len = str.length();
        Stack<Character> stack = new Stack<Character>();

        for (int idx = 0; idx < len; idx++) {
            if (arr[idx] == '{' || arr[idx] == '(' || arr[idx] == '[') {
                stack.push(arr[idx]);
            } else {
                if(arr[idx]==')'&&stack.pop()!='(') {
                    return false;
                }
                if(arr[idx]==']'&&stack.pop()!='[') {
                    return false;
                }
                if(arr[idx]=='}'&&stack.pop()!='{') {
                    return false;
                }
             }
        }
        System.out.println(Arrays.toString(stack.toArray()));
        return stack.isEmpty();
    }
}
