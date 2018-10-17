package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Department;
import com.mo.fang.springcloudsystem.system.event.LoadingDataEvent;
import com.mo.fang.springcloudsystem.system.mapper.DepartmentMapper;
import com.mo.fang.springcloudsystem.system.serviceI.DepartmentService;
import entity.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/30 0030
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public List<Department> getDeparts(Department department) {
        return departmentMapper.getDeparts(department);
    }


    @Transactional
    @Override
    public boolean saveOrUpdateDepartment(Department department) {
        int i = departmentMapper.saveOrUpdateDepartment(department);
        boolean flag = i<0?false:true;
        if (flag)
            applicationContext.publishEvent(new LoadingDataEvent(this,departmentMapper));
        else
            throw new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());
        return flag;
    }

    @Override
    public Department selectByPrimaryKey(Integer id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int i = departmentMapper.deleteByPrimaryKey(id);
        boolean flag = i<0?false:true;
        if (flag)
            applicationContext.publishEvent(new LoadingDataEvent(this,departmentMapper));
        else
            throw new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());

        return flag;
    }
}
