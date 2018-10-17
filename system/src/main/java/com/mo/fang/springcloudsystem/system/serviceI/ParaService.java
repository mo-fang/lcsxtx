package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.SysPara;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/28 0028
 */

public interface ParaService {
    List<SysPara> getAllParas(SysPara sysPara);
    boolean saveOrUpdate(SysPara sysPara);
    boolean delParaById(Integer id);
    SysPara selectByPrimaryKey(Integer id);
}
