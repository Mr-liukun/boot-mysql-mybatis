package com.example.demo.service;

import com.example.demo.model.Student;

public interface StudentService {

    Student getStudentById(int id);

    Student getStudentByIdAndName(int id, String name);

    boolean clearKey(int id, String name);


}
