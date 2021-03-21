package com.just.ytdemo.dao.mapper;

import com.just.ytdemo.dao.core.CommonMapper;
import com.just.ytdemo.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper extends CommonMapper<Student> {



    @Select({
     "select * from yt_student"
    })
    List<Student> queryAll();



}