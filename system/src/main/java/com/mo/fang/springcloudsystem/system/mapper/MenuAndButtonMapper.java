package com.mo.fang.springcloudsystem.system.mapper;

import com.mo.fang.springcloudsystem.system.entity.MenuAndButton;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface MenuAndButtonMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByMenuId(Integer id);

    int insert(MenuAndButton record);

    int insertSelective(MenuAndButton record);

    MenuAndButton selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuAndButton record);

    int updateByPrimaryKey(MenuAndButton record);

    List<MenuAndButton> getAllMenuAndButton(MenuAndButton menuAndButton);

    int insertBatch(@Param("mandbs")List<MenuAndButton> menuAndButtons);

    int delByMenuIdAndButtonId(@Param("menuId") Integer menuId, @Param("btnIdList")List btnIdList);

    Set<String> getMbAuthPerms(@Param("userId") Integer userId);
}