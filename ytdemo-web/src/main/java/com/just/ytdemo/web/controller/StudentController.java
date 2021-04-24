package com.just.ytdemo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.just.ytdemo.web.module.request.StudentRequest;
import com.just.ytdemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yutao.yan
 * @date 2018/12/3 15:11
 */
@RestController
@RequestMapping("student")
public class StudentController {


    @Reference
    private StudentService studentService;


    @RequestMapping("index")
    public String index(@RequestBody @Valid StudentRequest request){
//        System.out.println(1);
        return "欢迎你:"+ studentService.getStudent(request.getId());
    }


}
