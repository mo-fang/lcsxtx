package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.SysPara;

import java.util.List;

public interface SysParaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPara record);

    int insertSelective(SysPara record);

    SysPara selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPara record);

    int updateByPrimaryKey(SysPara record);

    List<SysPara> getAllParas(SysPara sysPara);

    int saveOrUpdate(SysPara sysPara);
}