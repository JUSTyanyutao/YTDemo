package com.just.ytdemo;

import java.util.Arrays;

public class SortTest {


//    public static Integer[] a = new Integer[]{5,3,7,4,9,2,4,6,0,1,8};
    public static Integer[] a = new Integer[]{5,3,4,7,6,10};

    /**
     * 快排
     *
     * 数据量大  用快排
     */
    public void quickSort(Integer[] a,int start, int end){

        //没有必要排序
        if (start >=  end) {
            return;
        }

        // 以最左边的为比较的数  准基
        int baseNum = a[start];
        int temp ;

        //  后面还要用到  start   和   end
        int i = start;
        int j = end;


        while ( i < j) {


            // 右边开始找 小于准基的
            while (i < j && a[j] >= baseNum ) {
                j--;
            }

            //  左边开始找 大于准基的
            while (i < j && a[i] <= baseNum) {
                i++;
            }



            //  2边都找到符合的数据  并且  i<j   将2边数据进行交换
            if ( i < j ) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        // 此时 i = j  相遇  这个数是个中间位置  把这个数 和准基 进行交换
        a[start] = a[i];
        a[i] = baseNum;

        quickSort(a,start,i-1);
        quickSort(a,i+1,end);
    }

    /**
     * 选择排序
     */
    public void xuanzeSort(Integer[] a){
        for(int i=0 ; i< a.length; i++) {
            for (int j= i+1 ; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

        /**
         * 冒泡排序
         */
    public void maopaoSort(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }








    public static void main(String[] args) {
        SortTest sortTest = new SortTest();
        sortTest.quickSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));

    }


}
