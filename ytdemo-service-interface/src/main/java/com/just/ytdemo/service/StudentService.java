package com.just.ytdemo.service;

import com.just.ytdemo.model.bo.StudentBO;

/**
 * @author yutao.yan
 * @date 2018/6/14 16:12
 */
public interface StudentService {

    StudentBO addStudent(StudentBO student);

    StudentBO getStudent(Long id);

    String play();
}
