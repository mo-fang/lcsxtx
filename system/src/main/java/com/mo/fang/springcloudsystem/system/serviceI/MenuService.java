package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.Button;
import com.mo.fang.springcloudsystem.system.entity.Menu;
import com.mo.fang.springcloudsystem.system.entity.SysUser;

import java.util.List;

public interface MenuService {
    List<Menu> getAllMenus(Menu menu);
    boolean saveOrUpdate(Menu menu,String[] buttons);
    List<Menu> getMenuByUser(SysUser user);
    List<Menu> getMenuist(SysUser user);
    String getMenuJson(SysUser user);
    boolean deleteByPrimaryKey(Integer id);
    Menu selectByPrimaryKey(Integer id);
}
