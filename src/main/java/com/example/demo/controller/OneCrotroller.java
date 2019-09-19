package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController // @RestController = @Controller(控制器) + @ResponseBody(返回json)
public class OneCrotroller {

    @Autowired
    private StudentService stuService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Student Index(HttpServletRequest request) {

        //获取cookie
        Cookie []cookie = request.getCookies();
        System.out.println("len:"+cookie.length);
        for (Cookie c: cookie) {
            System.out.println(c.getName() + " " +c.getValue());
        }


        //获取参数
        String username = request.getParameter("username");
        if (username == "" || username == null) {
            username = "1";
        }
        String idStr = request.getParameter("id");
        int id = Integer.valueOf(idStr);
        System.out.println(username);
        Student stu = stuService.getStudentById(id);
        return stu;
    }


}
