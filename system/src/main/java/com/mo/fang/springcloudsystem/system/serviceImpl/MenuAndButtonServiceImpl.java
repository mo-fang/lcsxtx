package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.MenuAndButton;
import com.mo.fang.springcloudsystem.system.mapper.MenuAndButtonMapper;
import com.mo.fang.springcloudsystem.system.serviceI.MenuAndButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * create by Mofang_ysc on 2018/9/21 0021
 */
@Service
public class MenuAndButtonServiceImpl implements MenuAndButtonService {
    @Autowired
    private MenuAndButtonMapper menuAndButtonMapper;


    @Override
    public Set<String> getMbAuthPerms(Integer id) {
        return menuAndButtonMapper.getMbAuthPerms(id);
    }

    @Override
    public List<MenuAndButton> getAllMenuAndButton(MenuAndButton menuAndButton) {
        return menuAndButtonMapper.getAllMenuAndButton(menuAndButton);
    }

    @Override
    public int delByMenuIdAndButtonId(Integer menuId, List btnIdList) {
        return menuAndButtonMapper.delByMenuIdAndButtonId(menuId,btnIdList);
    }

    @Transactional
    @Override
    public boolean insertBatch(List<MenuAndButton> menuAndButtons) {
        boolean flag = true;
        int i = menuAndButtonMapper.insertBatch(menuAndButtons);
        if (i<0)
            flag = false;
        return flag;
    }
}
