package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.mapper.SysUserMapper;
import com.mo.fang.springcloudsystem.system.serviceI.UserService;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import entity.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUser(SysUser user) {
        return sysUserMapper.getUser(user);
    }

    @Override
    public List<SysUser> getAllUsers(SysUser user) {
        return sysUserMapper.getAllUsers(user);
    }

    @Override
    public boolean saveOrUpdateUser(SysUser user) {
        int i = sysUserMapper.saveOrUpdateUser(user);
        boolean flag = i<0?false:true;
        if (!flag)
            throw new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());
        return flag;
    }
}
