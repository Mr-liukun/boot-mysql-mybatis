package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {

    Student getStudentInfoById(@Param("id") int id);

    Student getStudentInfoByIdAndName(@Param("id") int id, @Param("name") String name);

    Integer addStudent(Student stu);


}
