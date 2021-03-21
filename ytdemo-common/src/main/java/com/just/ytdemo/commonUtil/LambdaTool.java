package com.just.ytdemo.commonUtil;


import com.just.ytdemo.functionInterface.RunExecutor;
import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yutao.yan
 * @date 2018/6/14 11:05
 *
 * https://blog.csdn.net/u014205968/article/details/71484312
 * https://blog.csdn.net/u014205968/article/details/71484374
 *
 * https://www.zhihu.com/question/28565691
 *
 */
public class LambdaTool {


    /**
     *  功能性接口  函数式接口  是只包含一个抽象方法声明的接口
     *  @FunctionalInterface
     * @see com.just.ytdemo.functionInterface.RunExecutor
     */


    public void useLamdba() {

        /** 以前的写法 */
        new RunExecutor(){
            @Override
            public void execute() {
                System.out.println(1);
            }
        };



        /**   lamdba   1 */
        RunExecutor executor = () -> System.out.println(1);
        executor.execute();



        /**  带参数的 lamdba */
        Collections.sort(new ArrayList<Integer>(), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        /** 无敌  */
        Collections.sort(new ArrayList<Integer>() ,(Integer a,Integer b) -> { return a-b; });

    }


    @Data
    class User{

        Integer userId;
        boolean isVip;
        String name;


        User(Integer userId, boolean isVip, String name) {
            this.userId = userId;
            this.isVip = isVip;
            this.name = name;
        }

    }



    public static <T> List<T> pageBySubList(List<T> list, int pagesize, int currentPage) {
        int totalcount = list.size();
        int pagecount = 0;
        List<T> subList;
        int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        if (m == 0) {
            subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
        } else {
            if (currentPage == pagecount) {
                subList = list.subList((currentPage - 1) * pagesize, totalcount);
            } else {
                subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
            }
        }
        return subList;

    }






    /**
     * 对 集合的操作
     * @param args
     */
    public static void main(String[] args) {


        LambdaTool tool = new LambdaTool();

        ArrayList<User> users = new ArrayList<>();
        users.add(tool.new User(8, false, "小明"));
        users.add(tool.new User(2, true, "小红"));
        users.add(tool.new User(7, false, "小丽"));
        users.add(tool.new User(10, false, "小磊"));
        users.add(tool.new User(5, true, "小龙"));
        users.add(tool.new User(6, true, "小康"));
        users.add(tool.new User(3, true, "小智"));
        users.add(tool.new User(1, false, "小虎"));
        users.add(tool.new User(9, false, "小夫"));
        users.add(tool.new User(4, false, "小鸡"));

        Double a =  0.0003;

        String scaleStr = String.format("%.4f", a);
        System.out.println(scaleStr); // 0.0005


//        pageBySubList(users,2,1);

//        List<User> list = users.stream().filter(user ->  user.getUserId() <= 5 ).distinct(users.).collect(Collectors.toList());

        /** 遍历 id <=5的集合   filter */
//        List<User> list = users.stream().filter(user ->  user.getUserId() <= 5 ).collect(Collectors.toList());

        /** 排序   按userID从小到大排序   sorted  */
//        List<User> list = users.stream().sorted((u1,u2) -> u1.getUserId() - u2.getUserId() ).collect(Collectors.toList());


        /** 只取 前面 三个  limit  */
        /** 跳过 前面 三个  skip  */
//        List<User> list = users.stream().limit(3).collect(Collectors.toList());


        /** 取单独的字段  作为新的集合  */
//        List<Integer> list = users.stream().map(user ->  user.getUserId()).collect(Collectors.toList());
        List<Integer> list = users.stream().filter(user -> user.getUserId() > 3 ).map(User::getUserId).collect(Collectors.toList());

//        list.forEach((obj)-> System.out.println(obj.getUserId() + obj.getName() ) );
        list.forEach((obj)-> System.out.println(obj ) );


    }


}
