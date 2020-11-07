package com.just.ytdemo.demoTest;

import com.just.ytdemo.commonUtil.LambdaTool;
import lombok.Data;

import java.util.Date;

/**
 * @author yutao.yan
 * @date 2019/2/13 18:04
 */
public class LombokBuildTest {


    private String name;

    private Integer age;

    private Boolean vip;



    public static LombokBuildTest builder(){
        return new LombokBuildTest();
    }

    public LombokBuildTest name(String name){
        this.name = name;
        return this;
    }


    public LombokBuildTest age(Integer age){
        this.age = age;
        return this;
    }
//




    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public static void main(String[] args) {

//        String s = "1";
//
//        LombokBuildTest test1 = new LombokBuildTest();
//        LombokBuildTest test2 = new LombokBuildTest();
//        test1.setVip(false);


        Date time = new Date(1577807999000L);
        System.out.println(time);








//        switch (s){
//            case "1":
//                System.out.println(1);
//            case "2":
//            case "3":
//                System.out.println(2);
//            case "4":
//                break;
//
//        }



//        Long s = Long.valueOf("10.00");
//        System.out.println(s);
//        LombokBuildTest.builder().name("yyt").age(18).getName();





    }




}
