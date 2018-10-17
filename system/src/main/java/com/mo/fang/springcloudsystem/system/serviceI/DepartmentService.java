package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.Department;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/30 0030
 */

public interface DepartmentService {
    List<Department> getDeparts(Department department);
    boolean  saveOrUpdateDepartment(Department department);
    Department selectByPrimaryKey(Integer id);
    boolean  deleteByPrimaryKey(Integer id);
}
