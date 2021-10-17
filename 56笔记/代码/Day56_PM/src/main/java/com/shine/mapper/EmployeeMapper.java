package com.shine.mapper;

import com.shine.entity.Employee;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {
    @Select("select * from employee where id=#{id}")
    Employee selectEmployeeById(Integer id);

    @Select("select id,emp_name empName,salary,dept_id deptId from employee where id=#{id}")
    Employee selectEmployeeById2(Integer id);

    @Select("select * from employee where id=#{id}")
    @Results(id = "EmpRetMap",
        value = {
              @Result(column = "emp_name",property = "empName"),
              @Result(column = "dept_id",property = "deptId")
        })
    Employee selectEmployeeById3(Integer id);
}
