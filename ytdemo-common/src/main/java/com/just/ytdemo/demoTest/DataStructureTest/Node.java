package com.just.ytdemo.demoTest.DataStructureTest;

/**
 *
 *  链表的节点
 * @author yutao.yan
 * @date 2018/8/8 18:06
 */
public class Node<T> {

    //当前数据
    public T data;

    // 下一个链表节点
    public Node<T> next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }


    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
