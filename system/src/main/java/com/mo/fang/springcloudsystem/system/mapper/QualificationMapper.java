package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.Qualification;

import java.util.List;

public interface QualificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Qualification record);

    int insertSelective(Qualification record);

    Qualification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Qualification record);

    int updateByPrimaryKey(Qualification record);

    List<Qualification> getQualifications(Qualification qualification);
}