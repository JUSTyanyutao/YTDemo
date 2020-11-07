package com.just.ytdemo.demoTest.DataStructureTest;

/**
 * java实现单链表
 *
 * @see java.util.LinkedList  这个是双链表
 * @author yutao.yan
 * @date 2018/8/8 17:45
 */


public class MyLinkedList<T> {

    /**
     *  头节点
     */
    public Node<T> head;

    /**
     *  长度
     */
    public int length;


    /**
     * 向链表加数据
     * @param value
     */
    public void add(T value){

        Node<T> newData = new Node<>(value);

        Node<T> temp = head;

        if (temp == null) {
            head = newData;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newData;

        }
        length++;
    }


    /**
     * 打印 链表
     */
    public void printAll() {

        if (head != null) {
            System.out.println(head.data);
        }
        Node<T> temp = head.next;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    /**
     * 获取链表 值
     */
    public Node<T> get(int index) {
        Node<T> x = head;
        for(int i = 0; i<= index ; i++) {
            x = head.next;
        }
        return x;
    }



    /**
     * 向链表指定位置加数据
     * @param value
     */
    public void add(int index,T value){

        Node<T> newData = new Node<>(value);

        Node<T> temp = head;
        int count = 0;

        while (true) {
            if (count == index - 1) {
                newData.next = temp.next;
                temp.next = newData;
                break;
            }
            if (count != 0) {
                temp = temp.next;
            }
            count ++;
        }
        temp = newData;
        length++;
    }


    public static void main(String[] args) {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList();
        myLinkedList.add(1);
        myLinkedList.add(3);
        // 往第二个 位置 插入2
        myLinkedList.add(2,2);
        //  获取第二个位置的值
        System.out.println(myLinkedList.get(2).data);
        myLinkedList.printAll();









    }





















}
