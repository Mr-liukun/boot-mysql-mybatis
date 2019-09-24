package com.example.demo.serviceImp;
import com.example.demo.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImpTest {

    //@Autowired
    @Resource
    private StudentServiceImp studentServiceImp;

    @Test
    public void getStudentById() {

        int id = 1;

        Student stu = studentServiceImp.getStudentById(id);
        Assert.assertEquals("liu kun", stu.getName());


    }

    @Test
    public void getStudentByIdAndName() {

        String name = "li si";
        int id = 2;
        Student stu = studentServiceImp.getStudentByIdAndName(id, name);

        Assert.assertEquals(String.valueOf(789.0), stu.getScore()+"");
    }


    @Test
    public void clearKey() {
        String name = "liu kun";
        int id = 1;

        boolean result = studentServiceImp.clearKey(id, name);
        Assert.assertEquals(true, result);
    }
}