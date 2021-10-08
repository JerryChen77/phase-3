package com.shine.dao;

import com.shine.entity.Department;
import com.shine.entity.Employee;

import java.util.List;

public interface DepartmentMapper {
    Department findDeptWithEmpById(int id);

    Department findDeptById(int id);

    List<Employee> findEmpByDeptId(int deptId);

}
