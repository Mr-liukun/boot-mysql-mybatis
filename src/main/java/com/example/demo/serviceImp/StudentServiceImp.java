package com.example.demo.serviceImp;

import com.example.demo.cache.Redis;
import com.example.demo.controller.OneCrotroller;
import com.example.demo.mapper.StudentDao;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImp implements StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImp.class);
    //@Autowired
    @Resource
    private StudentDao studentDao;

    @Resource
    private Redis redis;


    @Override
    public Student getStudentById(int id) {
        return studentDao.getStudentInfoById(id);
    }

    @Override
    public Student getStudentByIdAndName(int id, String name) {

        String key = id + name;

        String value = redis.get(key);
        if(value != null && value != "") {
            try {
                Student student = (Student)redis.SeriaToObject(value);
                if(student == null){
                    log.error("对象解码出错");
                }
                log.info("从缓存获取: "+student.toString());
                return student;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        Student student = studentDao.getStudentInfoByIdAndName(id, name);
        try{
            String str = redis.ObjectToSeria(student);
            if(redis.set(key, str) == true) {
                log.info("redis缓存设置成功");
            }

        }catch(Exception e) {
            log.error("缓存设置失败");
            e.printStackTrace();

        }
        log.info("从数据库获取");
        return student;

    }

    @Override
    public boolean clearKey(int id, String name) {
        String key = id + name;
        if(redis.delete(key) == true) {
            log.info("删除缓存成功");
            return true;
        }
        log.info("删除缓存失败");
        return false;
    }
}
