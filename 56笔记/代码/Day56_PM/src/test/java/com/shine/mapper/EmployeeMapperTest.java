package com.shine.mapper;

import com.shine.entity.Employee;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

public class EmployeeMapperTest {
    @Test
    public void getEmpById(){
        EmployeeMapper employeeMapper = MyBatisUtils.getMapper(EmployeeMapper.class);

        Employee employee = employeeMapper.selectEmployeeById(6);
        System.out.println(employee);

    }

    @Test
    public void getEmpById2(){
        EmployeeMapper employeeMapper = MyBatisUtils.getMapper(EmployeeMapper.class);

        Employee employee = employeeMapper.selectEmployeeById2(6);
        System.out.println(employee);

    }

    @Test
    public void getEmpById3(){
        EmployeeMapper employeeMapper = MyBatisUtils.getMapper(EmployeeMapper.class);

        Employee employee = employeeMapper.selectEmployeeById3(6);
        System.out.println(employee);

    }
}
