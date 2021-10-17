package com.shine.mapper;

import com.shine.entity.Department;
import com.shine.entity.Employee;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

import java.util.List;

public class DepartmentMapperTest {

    @Test
    public void getDeptById(){
        DepartmentMapper departmentMapper = MyBatisUtils.getMapper(DepartmentMapper.class);

        Department department = departmentMapper.findDeptById(2);
        System.out.println(department);
    }

    @Test
    public void getEmpById(){
        DepartmentMapper departmentMapper = MyBatisUtils.getMapper(DepartmentMapper.class);

        List<Employee> employees = departmentMapper.findEmpByDeptId(2);
        System.out.println(employees);
    }


}
