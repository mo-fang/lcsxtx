package com.mo.fang.springcloudsystem.system.event;


import com.mo.fang.springcloudsystem.system.entity.*;
import com.mo.fang.springcloudsystem.system.mapper.*;
import com.mo.fang.springcloudsystem.system.serviceImpl.RedisServiceImpl;
import org.apache.catalina.startup.Catalina;
import org.springframework.context.ApplicationEvent;

import javax.annotation.Resource;
import java.util.List;


public class LoadingDataEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1149678082569464779L;

    //------------------------------------------菜单redis的重载-----------------------------------------------
    private MenuMapper menuMapper;

    public MenuMapper getMenuMapper() {
        return menuMapper;
    }

    public LoadingDataEvent(Object source, MenuMapper menuMapper) {
        super(source);
        this.menuMapper = menuMapper;
    }

    public void menu_reload(RedisServiceImpl redisService, String PREFIX) {
        redisService.remove(PREFIX + "MENUS");
        Menu menu = new Menu();
        List<Menu> menuList = menuMapper.getAllMenus(menu);
        redisService.set(PREFIX + "MENUS", menuList);
    }

    //------------------------------------------菜单redis的重载-------------------------------------------------

    private MenuAndButtonMapper menuAndButtonMapper;

    public MenuAndButtonMapper getMenuAndButtonMapper() {
        return menuAndButtonMapper;
    }

    public LoadingDataEvent(Object source, MenuAndButtonMapper menuAndButtonMapper) {
        super(source);
        this.menuAndButtonMapper = menuAndButtonMapper;
    }

    public void setMenuAndButton_reload(RedisServiceImpl redisService, String PREFIX) {
        redisService.remove(PREFIX + "MENUANDBUTTONS");
        List<MenuAndButton> menuAndButtons = menuAndButtonMapper.getAllMenuAndButton(new MenuAndButton());
        redisService.set(PREFIX + "MENUANDBUTTONS", menuAndButtons);
    }

    //------------------------------------------商品类型redis的重载-------------------------------------------------
    private CategoryMapper categoryMapper;
    public CategoryMapper getCategoryMapper(){
        return this.categoryMapper;
    }

    public LoadingDataEvent(Object source, CategoryMapper categoryMapper) {
        super(source);
        this.categoryMapper = categoryMapper;
    }

    public void setCategory_reload(RedisServiceImpl redisService, String PREFIX) {
        redisService.remove(PREFIX + "CATEGORYS");
        List<Category> categorys = categoryMapper.getAllCategorys(new Category());
        redisService.set(PREFIX + "CATEGORYS", categorys);
    }
    //------------------------------------------系统参数redis的重载-------------------------------------------------
    private SysParaMapper sysParaMapper;
    public SysParaMapper getSysParaMapper(){
        return this.sysParaMapper;
    }
    public LoadingDataEvent(Object source, SysParaMapper sysParaMapper) {
        super(source);
        this.sysParaMapper = sysParaMapper;
    }
    public void setSysPara_reload(RedisServiceImpl redisService, String PREFIX) {
        redisService.removePattern(PREFIX + "PARAS-*");
        List<SysPara> sysParas = sysParaMapper.getAllParas(new SysPara());
        sysParas.forEach(para->{
            redisService.set(PREFIX+"PARAS-"+para.getName(),para.getValue());
        });
    }
//--------------------------------------部门redis 重载-------------------------------------------------

    private DepartmentMapper  departmentMapper;

    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }
    public  LoadingDataEvent(Object source,DepartmentMapper departmentMapper){
        super(source);
        this.departmentMapper = departmentMapper;
    }
    public void setDepartment_reload(RedisServiceImpl redisService, String PREFIX) {
        redisService.remove(PREFIX+"DEPARTMENTS");
        List<Department> departments = departmentMapper.getDeparts(new Department());
        redisService.set(PREFIX+"DEPARTMENTS",departments);
    }
}
