package com.just.ytdemo.ServiceTest;

import com.just.ytdemo.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yutao.yan
 * @date 2018/7/24 17:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {


    @Autowired
    private StudentService studentService;

    @Test
    public void addStudentTest() {

//        BeanFactory bf = new DefaultListableBeanFactory() ;
//        Student s = (Student) bf.getBean("student");


//        Date now = new Date();
//        Student student = new Student();
//        student.setName("闫雨涛yt");
//        student.setAddTime(now);
//        student.setUpdateTime(now);
//        student.setId(15);
//        Student newStudent = studentService.addStudent(student);
//        System.out.println(1);

//        student.setId(1);
//        student = studentService.getStudent(1);
//        System.out.println(1);


////        RunExecutor executor = () -> Singleton.getInstance();
////        executor.execute();
//
//        for (int i = 0; i<100; i ++) {
////
//            new Thread(() -> Singleton.getInstance()).start();
//            new Thread(() -> Singleton.getInstance()).start();
//            new Thread(() -> Singleton.getInstance()).start();
//            new Thread(() -> Singleton.getInstance()).start();
//            new Thread(() -> Singleton.getInstance()).start();
//
//        }




    }




    @Test
    public void getStudentTest() {


        studentService.getStudent(1L);





    }


}
