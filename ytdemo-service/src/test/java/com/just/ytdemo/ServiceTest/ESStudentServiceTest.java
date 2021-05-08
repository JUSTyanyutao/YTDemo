package com.just.ytdemo.ServiceTest;

import com.just.ytdemo.service.EsStudentService;
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
public class ESStudentServiceTest {


    @Autowired
    private EsStudentService esStudentService;

    @Test
    public void addESStudentTest() {
        //创建索引
//        esStudentService.createIndex();
        // 增加数据
        esStudentService.addDocs();

        //是否存在 索引
//        esStudentService.existIndex();

        // 查询索引
//        esStudentService.getIndex();


    }




    @Test
    public void getESStudentTest() {
        esStudentService.queryConditons();
    }


}
