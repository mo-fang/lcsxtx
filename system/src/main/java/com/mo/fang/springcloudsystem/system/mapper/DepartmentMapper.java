package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getDeparts(Department department);

    int saveOrUpdateDepartment(Department department);
}