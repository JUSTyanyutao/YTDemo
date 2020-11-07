package com.just.ytdemo.demoTest.CollectionTest;//package com.just.ytdemo.demoTest.CollectionTest;
//
//import com.just.ytdemo.entity.Student;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
///**
// * 反射
// *
// * https://blog.csdn.net/sinat_38259539/article/details/71799078
// * @author yutao.yan
// * @date 2018/8/7 14:38
// */
//public class ReflexTest {
//
//
//    public static void main(String[] args) throws Exception{
//
//
//        /**
//         * 获取到  反射对象  三种方式
//         */
////        Student student = new Student();
////        Class studentClass = student.getClass();
//        Class studentClass = Student.class;
//
//        // 获取类的名称
//        System.out.println(studentClass.getName());
//
//
//        /**
//         * 获取 累的 属性
//         */
//
//        // 获取共有属性  public
//        Field[] publicFields =  studentClass.getFields();
//        for (Field obj : publicFields) {
//            System.out.println(obj);
//        }
//
//
//        // 获取所有属性
//        Field[] allFields =  studentClass.getDeclaredFields();
//        for (Field obj : allFields) {
//            System.out.println(obj + ".........type:" +obj.getType());
//        }
//
//        Field nameField = studentClass.getDeclaredField("name");
//
//        //获取一个对象
//        Object object = studentClass.getConstructor().newInstance();
//
//        //暴力反射，解除私有限定  public  无需操作
//        nameField.setAccessible(true);
//
//        nameField.set(object,"孙悟空");
//        System.out.println(((Student)object).getName());
//
//
//        /**
//         * 获取 方法  并使用
//         */
//        //获取所有共有的方法
//        Method[] methodArray = studentClass.getMethods();
//        for(Method m : methodArray){
//            System.out.println(m);
//        }
//        System.out.println("***************获取所有的方法，包括私有的*******************");
//        methodArray = studentClass.getDeclaredMethods();
//        for(Method m : methodArray){
//            System.out.println(m);
//        }
//        System.out.println("***************获取公有的show1()方法*******************");
//        Method m = studentClass.getMethod("setName", String.class);
//        System.out.println(m);
//        //实例化一个Student对象
//        Object obj = studentClass.getConstructor().newInstance();
//        m.invoke(obj, "刘德华");
//        System.out.println(((Student)obj).getName());
////        System.out.println("***************获取私有的show4()方法******************");
////        m = stuClass.getDeclaredMethod("show4", int.class);
////        System.out.println(m);
////        m.setAccessible(true);//解除私有限定
////        Object result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
////        System.out.println("返回值：" + result);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//}
