package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.Menu;
import com.mo.fang.springcloudsystem.system.entity.MenuAndButton;
import com.mo.fang.springcloudsystem.system.entity.Role;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/22 0022
 */

public interface RoleService {
    boolean saveOrUpdateRole(Role role);
    List<Role> getAllRoles(Role role);
    String getAllAuth(List<Menu> menuList, List<MenuAndButton> menuAndButtons,Integer id);
    boolean doAuthRole(Integer id,Integer[] mbid);
    boolean doDelRole(Integer id);
    Role selectByPrimaryKey(Integer id);

}
