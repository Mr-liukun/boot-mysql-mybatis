package com.example.demo.service;

import com.example.demo.model.Student;

public interface StudentService {

    Student getStudentById(int id);

    Student getStudentByIdAndName(int id, String name);

}
