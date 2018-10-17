package com.mo.fang.springcloudsystem.system.controller;

import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.Menu;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import com.mo.fang.springcloudsystem.system.serviceI.MenuService;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import com.mo.fang.springcloudsystem.system.util.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/15 0015
 */
@RestController
public class WelController {
    @Autowired
    private ViewAdapter adapter;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisService redisService;
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;

    @GetMapping("toIndex.html")
    public ModelAndView toIndex(ModelAndView modelAndView){
        SysUser loginUser = SysUtil.getLoginUser();
        String SYSNAME = (String)redisService.get(PREFIX + "PARAS-SYSNAME");
        List<Menu> menus = menuService.getMenuist(loginUser);
        String viewName = adapter.viewAdapter("index");
        modelAndView.addObject("SYSNAME",SYSNAME);
        modelAndView.addObject("user",loginUser);
        modelAndView.addObject("menus",menus);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
    @GetMapping("getMenuJson.html")
    public String getMenuJson(){
        SysUser loginUser = SysUtil.getLoginUser();
       String menuString = menuService.getMenuJson(loginUser);
        return menuString;
    }


    @GetMapping("toCenter.html")
    public ModelAndView toCenter(ModelAndView modelAndView){
        SysUser loginUser = SysUtil.getLoginUser();
        String viewName = adapter.viewAdapter("center");
        modelAndView.addObject("user",loginUser);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
