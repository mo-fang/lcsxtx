package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Auth;
import com.mo.fang.springcloudsystem.system.mapper.AuthMapper;
import com.mo.fang.springcloudsystem.system.serviceI.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/25 0025
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Transactional
    @Override
    public boolean insertBatch(List<Auth> list) {
        final int i = authMapper.insertBatch(list);
        boolean flag = i<0?false:true;
        return flag;
    }
}
