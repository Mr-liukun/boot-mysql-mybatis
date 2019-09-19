package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {

    Student getStudentInfoById(@Param("id") int id);
}
