package test;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ruijie.ioc.App;
import com.ruijie.ioc.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes=App.class)// 指定spring-boot的启动类  
public class StudentTest2 {
    @Autowired
    private StudentService studentService;
    @Test
    public void likeName() {
        System.out.println("aa22");
        System.out.println(studentService.findStudentByRedis(1));
//      assertTrue(studentService.likeName("小明2").size() > 0);
    }
    @Test
    public void likeName2() {
        System.out.println("bb222");
//      assertTrue(studentService.likeName("小明2").size() > 0);
    }
}
