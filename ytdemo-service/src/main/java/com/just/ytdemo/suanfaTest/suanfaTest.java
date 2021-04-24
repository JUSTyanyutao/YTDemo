package com.just.ytdemo.suanfaTest;

import com.just.ytdemo.demoTest.DataStructureTest.Node;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

public class suanfaTest {


    /**
     * 从数组中找出第K个最大的数
     *
     * 基于快排的思想
     *
     * @param arr
     * @param k
     */
    public static void getMaxAmount(int[] arr ,int k){

    }


    /**
     * 快排
     * @param arr
     */
    public static void quickSort(int[] arr){




    }





    /**
     * 从数组中求最长 不重复的序列
     * @param arr
     */
    public static void getMaxStr(String[] arr){

        if (arr.length == 0) {
            return;
        }
        int length = arr.length;
        int result = 0;
        int begin = 0,end = 0;
        Set<String> set = new HashSet();
        while ( begin < length && end < length) {
            if (set.contains(arr[end])) {
                set.remove(arr[begin]);
                begin++;
            } else {
                set.add(arr[end]);
                end++;
                result = Math.max(result,end-begin);
            }
        }
        System.out.println("从数组中求最长 不重复的序列:"+result);
    }


    /**
     * 给定一个数组 和一个数  2数之和相等
     * @param arr
     * @param k
     */
    public static void getSum(int[] arr , int k) {

    }



    /**
     * 求字符串最大不同子串,并且输出
     * @param str
     */
    public static void getMaxStr(String str){
        if (StringUtils.isEmpty(str)) {
            return;
        }

        int n = str.length();
        int result = 0;
        int end=0, start = 0;
        List<String> list = new ArrayList<>();
        Set<Character> set=new HashSet<>();
        while(start<n && end<n){
            if(set.contains(str.charAt(end))){
                set.remove(str.charAt(start++));
            } else{
                set.add(str.charAt(end++));
                if (end-start > result) {
                    list.clear();
                    list.add(str.substring(start,end));
                }
                if (end -start == result) {
                    list.add(str.substring(start,end));
                }
                result=Math.max(result,end-start);
            }
        }
        System.out.println(result);
        System.out.println(list.toString());
    }


    public static void getMaxStr1(String str){
        if (StringUtils.isEmpty(str)) {
            return;
        }
        // 长度
        int length = str.length();
        // 结果
        int result = 0;

        int begin = 0 , end = 0;

        Set<Character> set = new HashSet<>();

        for (; begin<length && end < length;) {
            if (set.contains(str.charAt(end))) {
                set.remove(str.charAt(begin));
                begin++;
            } else {
                set.add(str.charAt(end));
                end++;
                result = Math.max(result,end-begin);
            }
        }
        System.out.println(result);
    }
























    /**
     * 求和 2个链表 从高位到低位 求和
     */
    public static void getSum(){

    }

    /**
     * 链表翻转
     * https://www.cnblogs.com/keeya/p/9218352.html
     *    遍历法
     *
     *    preNode  保存前节点
     *    nextNode 记录当前节点的下一个节点
     *
     */
    public static void nodeRese1(Node node){
        Node nextNode = null;
        Node preNode = null;

        while (node != null) {
            nextNode = node.next;
            node.next = preNode;
            preNode = node;
            node = nextNode;
        }
        System.out.println(preNode.toString());
    }


    /**
     * 链表翻转
     * https://www.cnblogs.com/keeya/p/9218352.html
     *    递归法
     *
     *
     *
     */
    public static void nodeRese2(Node node){
        Node nextNode = null;
        Node preNode = null;

        while (node != null) {
            nextNode = node.next;
            node.next = preNode;
            preNode = node;
            node = nextNode;
        }
        System.out.println(preNode.toString());
    }





    public static void main(String[] args) {

        String str1 = "123阿迪达斯adidas男女休闲鞋DB";
        getMaxStr(str1);

        getMaxStr1(str1);

        // 链表
//        Node<String> n1= new Node<>();
//        Node<String> n2= new Node<>();
//        Node<String> n3= new Node<>();
//        Node<String> n4= new Node<>();
//        n1.data = "A";
//        n2.data = "B";
//        n3.data = "C";
//        n4.data = "D";
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        nodeRese1(n1);


    }
}
