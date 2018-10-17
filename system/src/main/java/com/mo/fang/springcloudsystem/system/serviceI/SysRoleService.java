package com.mo.fang.springcloudsystem.system.serviceI;

import java.util.Set;

public interface SysRoleService {
    Set<String> findRoleNameByUserId(int userId);
}
