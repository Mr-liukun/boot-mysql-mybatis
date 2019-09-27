package com.example.demo.serviceImp;

import com.example.demo.cache.Redis;
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

        Student value = (Student)redis.get(key);
        if(value != null ) {
            log.info("从缓存获取: " + value.toString());
            return value;
        }

        Student student = studentDao.getStudentInfoByIdAndName(id, name);

            //String str = redis.ObjectToSeria(student);
        if (redis.set(key, student) == true) {
            log.info("redis缓存设置成功");
        }else{
            log.error("redis缓存设置失败");
        }
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

    @Override
    public boolean addStudent(String name, float score) {
        Student stu  = new Student();
        stu.setName(name);
        stu.setScore(score);

        Integer re = studentDao.addStudent(stu);
        Integer result = stu.getId();

        log.info("id:", result);
        log.info("id:", re);

        if (result == 0) {
            return false;
        } else {
            return true;
        }

    }
}
