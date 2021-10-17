package com.shine;

import com.shine.entity.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {
    @Test
    public void getStu(){
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext01.xml");

        Student student = ioc.getBean("student", Student.class);
        System.out.println(student);

    }

}
