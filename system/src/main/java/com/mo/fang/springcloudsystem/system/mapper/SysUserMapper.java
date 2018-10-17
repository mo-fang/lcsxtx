package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysUserMapper {
    SysUser getUser(SysUser user);

    List<SysUser> getAllUsers(SysUser user);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    int saveOrUpdateUser(SysUser record);
}