package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.mapper.SysUserMapper;
import com.mo.fang.springcloudsystem.system.serviceI.UserService;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import entity.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.MapperFlag;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserById(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser getUser(SysUser user) {
        return sysUserMapper.getUser(user);
    }

    @Override
    public List<SysUser> getAllUsers(SysUser user) {
        return sysUserMapper.getAllUsers(user);
    }

    @Transactional
    @Override
    public boolean saveOrUpdateUser(SysUser user) {
        int i = sysUserMapper.saveOrUpdateUser(user);
        boolean flag = i<0?false:true;
        if (!flag)
            throw new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());
        return flag;
    }

    @Transactional
    @Override
    public boolean doDelUser(Integer id){
        int i = sysUserMapper.deleteByPrimaryKey(id);
        boolean flag = MapperFlag.tOrf(i);
        if (!flag)
            throw new RuntimeException(CodeMsg.DEL_FAIL.toString());
        return flag;
    }




    @Transactional
    @Override
    public boolean changePwd(SysUser record) {
        int i = sysUserMapper.changePwd(record);
        boolean flag = i<0?false:true;
        if (!flag)
            throw new RuntimeException(CodeMsg.CHANGE_UPDATEPWD_ERROR.toString());
        return flag;
    }
}
