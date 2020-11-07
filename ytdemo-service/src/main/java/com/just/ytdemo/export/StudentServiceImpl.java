package com.just.ytdemo.export;

import com.just.ytdemo.dao.mapper.StudentMapper;
import com.just.ytdemo.entity.Student;
import com.just.ytdemo.model.bo.StudentBO;
import com.just.ytdemo.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author yutao.yan
 * @date 2018/7/24 16:47
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentBO addStudent(StudentBO studentBO) {

        Student student = new Student();
        BeanUtils.copyProperties(studentBO,student);
        int result = studentMapper.insertUseGeneratedKeys(student);
        if (result >= 1) {
            BeanUtils.copyProperties(student,studentBO);
            return studentBO;
        }
        return new StudentBO();
    }



    @Override
    public StudentBO getStudent(Long id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        StudentBO studentBO = new StudentBO();
        if (student != null) {
            BeanUtils.copyProperties(student,studentBO);
        }
        return studentBO;
    }

    @Override
    public String play() {
        return "success";
    }
}
