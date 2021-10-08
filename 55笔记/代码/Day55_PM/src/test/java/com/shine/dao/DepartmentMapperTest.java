package com.shine.dao;

import com.shine.entity.Department;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

public class DepartmentMapperTest {
    @Test
    public void getDeptWithEmpById(){
        DepartmentMapper departmentMapper = MyBatisUtils.getMapper(DepartmentMapper.class);

        Department department = departmentMapper.findDeptWithEmpById(2);
        System.out.println(department);
        System.out.println(department.getEmployees());

        MyBatisUtils.closeSession();
    }


    @Test
    public void getDeptById(){
        DepartmentMapper departmentMapper = MyBatisUtils.getMapper(DepartmentMapper.class);

        Department department = departmentMapper.findDeptById(2);
        System.out.println(department);
        System.out.println(department.getEmployees());

        MyBatisUtils.closeSession();
    }

}
