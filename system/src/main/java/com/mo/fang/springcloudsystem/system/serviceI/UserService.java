package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.SysUser;
import com.mo.fang.springcloudsystem.system.util.SysUtil;

import java.util.List;

public interface UserService {
    SysUser getUserById(Integer  id);
    SysUser getUser(SysUser user);
    List<SysUser> getAllUsers(SysUser user);
    boolean saveOrUpdateUser(SysUser user);
}
