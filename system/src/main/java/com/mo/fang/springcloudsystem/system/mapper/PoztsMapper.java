package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.Pozts;

import java.util.List;

public interface PoztsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pozts record);

    int insertSelective(Pozts record);

    Pozts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pozts record);

    int updateByPrimaryKey(Pozts record);

    List<Pozts> getPoztses(Pozts pozts);
}