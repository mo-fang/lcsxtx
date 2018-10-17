package com.mo.fang.springcloudsystem.system.serviceI;

import com.mo.fang.springcloudsystem.system.entity.MenuAndButton;

import java.util.List;
import java.util.Set;

/**
 * create by Mofang_ysc on 2018/9/21 0021
 */

public interface MenuAndButtonService {
    Set<String> getMbAuthPerms(Integer id);
    List<MenuAndButton> getAllMenuAndButton(MenuAndButton menuAndButton);
    int delByMenuIdAndButtonId(Integer menuId,List btnIdList);
    boolean  insertBatch(List<MenuAndButton> menuAndButtons);
}
