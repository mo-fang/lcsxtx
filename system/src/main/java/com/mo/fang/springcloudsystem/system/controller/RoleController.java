package com.mo.fang.springcloudsystem.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.Menu;
import com.mo.fang.springcloudsystem.system.entity.MenuAndButton;
import com.mo.fang.springcloudsystem.system.entity.Role;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import com.mo.fang.springcloudsystem.system.serviceI.RoleService;
import com.mo.fang.springcloudsystem.system.util.SysUtil;
import entity.CodeMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.LayUtil;
import utils.Result;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/22 0022
 */

@RestController
public class RoleController {
    @Autowired
    private ViewAdapter adapter;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisService redisService;
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;
    private Gson gson = new Gson();

    @RequiresPermissions("rolemsg:search")
    @GetMapping("toRoleList.html")
    public ModelAndView toMenuList(ModelAndView modelAndView) {
        modelAndView.setViewName(adapter.viewAdapter("role/rolelist"));
        return modelAndView;
    }
    @RequiresPermissions("rolemsg:add")
    @GetMapping("toAddRole.html")
    public ModelAndView toAddRole(ModelAndView modelAndView) {
        modelAndView.setViewName(adapter.viewAdapter("role/add"));
        return modelAndView;
    }
    @RequiresPermissions("rolemsg:edit")
    @GetMapping("{id}/toEidtRole.html")
    public ModelAndView toEidtRole(ModelAndView modelAndView,@PathVariable("id") Integer id) {
        Role role = roleService.selectByPrimaryKey(id);
        modelAndView.addObject("role",role);
        modelAndView.setViewName(adapter.viewAdapter("role/edit"));
        return modelAndView;
    }
    @RequiresPermissions("rolemsg:add")
    @PostMapping("doAddRole.html")
    public String doAddRole(Role role){
        Result result = Result.success();
        boolean b = roleService.saveOrUpdateRole(role);
        if (!b)
            result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        return gson.toJson(result);
    }
    @RequiresPermissions("rolemsg:auth")
    @GetMapping("{id}/toAuthRole.html")
    public ModelAndView toAuthRole(ModelAndView modelAndView, @PathVariable("id")Integer id) {
        modelAndView.addObject("id",id);
        modelAndView.setViewName(adapter.viewAdapter("role/auth"));
        return modelAndView;
    }
    @RequiresPermissions("rolemsg:auth")
    @GetMapping("{id}/doGetAuth.html")
    public String doGetAuth(ModelAndView modelAndView,@PathVariable("id")Integer id){
        List<Menu> listMenu = (List<Menu>)redisService.get(PREFIX + "MENUS");
        List<MenuAndButton> menuAndButtons= (List<MenuAndButton>)redisService.get(PREFIX + "MENUANDBUTTONS");
        String allAuth = roleService.getAllAuth(listMenu, menuAndButtons,id);
        return allAuth;

    }
    @RequiresPermissions("rolemsg:auth")
    @PostMapping("doAuthRole.html")
    public String doAuthRole(Integer id,Integer[] mbid){
        Result result = Result.success();
        boolean b = roleService.doAuthRole(id, mbid);
        if(!b)
            result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        else
            SysUtil.clearAuth();
        return gson.toJson(result);
    }
    @RequiresPermissions("rolemsg:del")
    @PostMapping("doDelRole.html")
    public String doDelRole(Integer id){
        Result result = Result.success();
        boolean b = roleService.doDelRole(id);
        if(!b)
            result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        return gson.toJson(result);
    }

    @RequiresPermissions("rolemsg:search")
    @PostMapping("roleList.html")
    public String menuList(Integer page, Integer limit, Role role) {
        PageHelper.startPage(page, limit);
        Page<Object> pagehelperPage = PageHelper.getLocalPage();
        List<Role> roleList = roleService.getAllRoles(role);
        String layjson = LayUtil.getLayJsonWeChat(roleList, pagehelperPage.getTotal(), page);
        return layjson;
    }
}
