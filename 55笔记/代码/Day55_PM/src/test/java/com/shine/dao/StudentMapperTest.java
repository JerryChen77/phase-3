package com.shine.dao;

import com.shine.entity.Student;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

public class StudentMapperTest {
    @Test
    public void getStuWithSubById(){
        StudentMapper studentMapper = MyBatisUtils.getMapper(StudentMapper.class);

        Student student = studentMapper.findStuWithSubById(1002);
        System.out.println(student);
        System.out.println(student.getSubjects());

        MyBatisUtils.closeSession();
    }
}
