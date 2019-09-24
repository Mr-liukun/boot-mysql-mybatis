package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.serviceImp.StudentServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//@RestController // @RestController = @Controller(控制器) + @ResponseBody(返回json)
@Controller
public class OneCrotroller {

    @Autowired
    private StudentServiceImp stuServiceImp;

    private static final Logger log = LoggerFactory.getLogger(OneCrotroller.class);

    @RequestMapping("/")
    public String Hello(Model model) {

        model.addAttribute("hello", "你好，刘坤");

        return "page/hello";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String Index(HttpServletRequest request, Model model) {

//        //获取cookie
//        Cookie []cookie = request.getCookies();
//        System.out.println("len:"+cookie.length);
//        for (Cookie c: cookie) {
//            System.out.println(c.getName() + " " +c.getValue());
//        }

        //获取参数
        //String username = request.getParameter("username");
//        if (username == "" || username == null) {
//            username = "1";
//        }
        String idStr = request.getParameter("id");
        int id = Integer.valueOf(idStr);
        //System.out.println(username);
        Student stu = stuServiceImp.getStudentById(id);
        model.addAttribute("student", stu);
        return "page/demo";
    }

    @ResponseBody
    @RequestMapping(value = "/one")
    public Student One(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        int id = Integer.valueOf(idStr);
        Student stu = stuServiceImp.getStudentById(id);
        log.info("qweaaaaaaaaaa");
        return stu;
    }

    @ResponseBody
    @RequestMapping(value = "/stu")
    public Student findStudentByIdAndName(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        int id = Integer.valueOf(idStr);
        Student stu = stuServiceImp.getStudentByIdAndName(id, name);
        return stu;
    }

    @ResponseBody
    @RequestMapping("/clear")
    public boolean clearKey(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        int id = Integer.valueOf(idStr);
        if(stuServiceImp.clearKey(id, name)){
            return true;
        }
        return false;
    }





}
