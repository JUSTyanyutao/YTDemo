package com.just.ytdemo.demoTest.CollectionTest;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author yutao.yan
 * @date 2018/8/7 14:29
 */
public class ArrayListTest {


    public static void main(String[] args) throws Exception{

        ArrayList<Integer> list = new ArrayList<Integer>(1);
        for (int i=0; i<5; i++){
            list.add(i);
        }
        System.out.println(list.size());
//
//        //反射
        Class listClass = ((Object)list).getClass();
        Field field = listClass.getDeclaredField("elementData");
        field.setAccessible(true);

        Object[] o=(Object[]) field.get(list);
        System.out.println(o.length);

//        Field field1 = listClass.getDeclaredField("DEFAULT_CAPACITY");
//        field1.setAccessible(true);
//
//        System.out.println(field1.get(list));


////        System.out.println(((Object[])).length);


    }
}
