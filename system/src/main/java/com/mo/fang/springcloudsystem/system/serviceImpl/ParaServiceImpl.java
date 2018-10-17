package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.SysPara;
import com.mo.fang.springcloudsystem.system.event.LoadingDataEvent;
import com.mo.fang.springcloudsystem.system.mapper.SysParaMapper;
import com.mo.fang.springcloudsystem.system.serviceI.ParaService;
import com.sun.org.apache.bcel.internal.classfile.Code;
import entity.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/28 0028
 */
@Service
public class ParaServiceImpl implements ParaService {
    @Autowired
    private SysParaMapper sysParaMapper;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public List<SysPara> getAllParas(SysPara sysPara) {

    return sysParaMapper.getAllParas(sysPara);
    }

    @Transactional
    @Override
    public boolean saveOrUpdate(SysPara sysPara) {
        int i = sysParaMapper.saveOrUpdate(sysPara);
        boolean flag = i<0?false:true;
        if (flag)
            applicationContext.publishEvent(new LoadingDataEvent(this,sysParaMapper));
            else
                throw new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());
        return flag;
    }

    @Transactional
    @Override
    public boolean delParaById(Integer id) {
        int i = sysParaMapper.deleteByPrimaryKey(id);
        boolean flag = i<0?false:true;
        if (flag)
            applicationContext.publishEvent(new LoadingDataEvent(this,sysParaMapper));
        else
            throw new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());
        return flag;
    }

    @Override
    public SysPara selectByPrimaryKey(Integer id) {
        return sysParaMapper.selectByPrimaryKey(id);
    }
}
