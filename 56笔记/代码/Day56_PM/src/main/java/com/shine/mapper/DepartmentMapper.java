package com.shine.mapper;

import com.shine.entity.Department;
import com.shine.entity.Employee;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface DepartmentMapper {

    @Select("select * from employee where dept_id=#{id}")
    @Results(id = "EmpRetMap",
            value = {
                    @Result(column = "emp_name",property = "empName"),
                    @Result(column = "dept_id",property = "deptId")
            })
    List<Employee> findEmpByDeptId(Integer id);



    @Select("select * from department where id=#{id}")
    @Results(id = "DeptRetMap",
    value = {
            @Result(column = "dept_name",property = "deptName"),
            @Result(column = "id",
                    property = "employees",
                    many = @Many(select = "com.shine.mapper.DepartmentMapper.findEmpByDeptId",fetchType= FetchType.EAGER)
            )
    })
    Department findDeptById(Integer id);
}
