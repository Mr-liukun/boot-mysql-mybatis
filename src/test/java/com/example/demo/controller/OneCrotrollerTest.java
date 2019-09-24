package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.serviceImp.StudentServiceImp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneCrotrollerTest {

    @Autowired
    private StudentServiceImp serviceImp;

    @Test
    public void hello() {

    }

    @Test
    public void index() {
        //String idStr = request.getParameter("id");
        int id = 2;
        //System.out.println(username);
        Student stu = serviceImp.getStudentById(id);

        Assert.assertEquals("li si", stu.getName());

    }

    @Test
    public void one() {
    }
}