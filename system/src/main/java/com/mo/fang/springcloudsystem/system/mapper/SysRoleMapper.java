package com.mo.fang.springcloudsystem.system.mapper;

import entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SysRoleMapper {
    Set<String> findRoleNameByUserId(int userId);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}