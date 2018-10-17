package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.ShiroProtected;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiroProtectedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShiroProtected record);

    int insertSelective(ShiroProtected record);

    ShiroProtected selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShiroProtected record);

    int updateByPrimaryKey(ShiroProtected record);

    List<ShiroProtected> selectAll();
}