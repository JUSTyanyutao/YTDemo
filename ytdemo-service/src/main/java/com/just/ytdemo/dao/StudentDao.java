package com.just.ytdemo.dao;

import com.just.ytdemo.dao.mapper.StudentMapper;
import com.just.ytdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yutao.yan
 * @date 2018/11/12 17:10
 */
@Component
public class StudentDao extends CommonDAO<StudentMapper,Student> {

    private StudentMapper studentMapper;


    @Autowired
    public StudentDao(StudentMapper mapper) {
        super(mapper);
        this.studentMapper = mapper;
    }



    public List<Student> queryAll(){
        return studentMapper.selectAll();
    }


}
