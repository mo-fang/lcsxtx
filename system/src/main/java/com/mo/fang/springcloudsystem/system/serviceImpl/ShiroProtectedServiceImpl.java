package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.ShiroProtected;
import com.mo.fang.springcloudsystem.system.mapper.ShiroProtectedMapper;
import com.mo.fang.springcloudsystem.system.serviceI.ShiroProtectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/14 0014
 */
@Service
public class ShiroProtectedServiceImpl implements ShiroProtectedService {
    @Autowired
    ShiroProtectedMapper shiroProtectedMapper;

    public List<ShiroProtected> selectAll(){
        return  shiroProtectedMapper.selectAll();
    };
}
