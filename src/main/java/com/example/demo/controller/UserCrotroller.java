package com.example.demo.controller;

import com.example.demo.serviceImp.StudentServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserCrotroller {

    @Autowired
    private StudentServiceImp stuServiceImp;

    private static final Logger log = LoggerFactory.getLogger(OneCrotroller.class);


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStuHtml() {

        return "page/add_student";

    }

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public boolean addStudent(@RequestParam(value = "name") String name, @RequestParam(value = "score")float score) {

        if( name.trim() != "" && stuServiceImp.addStudent(name, score)) {
            return true;
        }
        return false;
    }


}
