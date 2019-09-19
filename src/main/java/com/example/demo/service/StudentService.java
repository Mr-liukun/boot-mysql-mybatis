package com.example.demo.service;

import com.example.demo.mapper.StudentDao;
import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService {


    @Resource
    private StudentDao studentDao;

    public Student getStudentById(int id) {

        return studentDao.getStudentInfoById(id);

    }


}
