package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllRoles(Role role);

    int saveOrUpdateRole(Role role);

    int delAuthAndUandRole(Integer id);

    int delAuthByOwnerId(Integer id);

    int  addUandR(@Param("userid") Integer id, @Param("roleid") Integer roleid);

    int deleteuAndRByuserId(@Param("userid")Integer userId);
}