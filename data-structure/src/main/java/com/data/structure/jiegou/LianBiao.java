package com.data.structure.jiegou;



/**
 * @ClassName: LianBiao
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/6/28$ 11:29$
 * @Version: 1.0
 */
public class LianBiao<T> {

    private class Node {
        private T t;
        private Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t) {
            this(t, null);
        }
    }

    private Node head;

    private Node tail;

    private int size;
    //构造函数
    public LianBiao(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    public void addFirst(T t){
        Node n = new Node(t);
        this.head = n;
    }

    public void add(T t){
      if ( this.head==null ){
          this.addFirst(t);
      }else {

      }
    }


}
