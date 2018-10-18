package com.mo.fang.springcloudsystem.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.*;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import com.mo.fang.springcloudsystem.system.serviceI.UserService;
import com.mo.fang.springcloudsystem.system.util.SysUtil;
import entity.CodeMsg;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.LayUtil;
import utils.Md5Util;
import utils.Result;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/18 0018
 */

@RestController
public class UserController {
    @Autowired
    private ViewAdapter adapter;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;

    private Gson gson = new Gson();

    @RequiresPermissions("usermsg:search")
    @GetMapping("toUserList.html")
    public ModelAndView toUserList(ModelAndView modelAndView){
        List<Department> departments = (List<Department>)redisService.get(PREFIX+"DEPARTMENTS");
        List<Qualification> qualifications = (List<Qualification>)redisService.get(PREFIX+"QUALIFICATIONS");
        List<Pozts>  poztses= (List<Pozts>)redisService.get(PREFIX+"POZTSES");
        modelAndView.addObject("departments",departments);
        modelAndView.addObject("qualifications",qualifications);
        modelAndView.addObject("poztses",poztses);
        String viewAdapter = adapter.viewAdapter("user/userlist");
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @GetMapping("toUserInfo.html")
    public ModelAndView toUserInfo(ModelAndView modelAndView){
        SysUser loginUser = SysUtil.getLoginUser();
//        List<Department> departments = (List<Department>)redisService.get(PREFIX+"DEPARTMENTS");
        List<Qualification> qualifications = (List<Qualification>)redisService.get(PREFIX+"QUALIFICATIONS");
//        List<Pozts>  poztses= (List<Pozts>)redisService.get(PREFIX+"POZTSES");
//        modelAndView.addObject("departments",departments);
        modelAndView.addObject("qualifications",qualifications);
//        modelAndView.addObject("poztses",poztses);
        modelAndView.addObject("user",loginUser);
        String viewAdapter = adapter.viewAdapter("user/userInfo");
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }

    @RequiresPermissions("usermsg:add")
    @GetMapping("toAddUser.html")
    public ModelAndView toAddUser(ModelAndView modelAndView){
        List<Department>  departments = (List<Department>)redisService.get(PREFIX+"DEPARTMENTS");
        List<Qualification>  qualifications = (List<Qualification>)redisService.get(PREFIX+"QUALIFICATIONS");
        List<Pozts>  poztses = (List<Pozts>)redisService.get(PREFIX+"POZTSES");
        modelAndView.addObject("departments",departments);
        modelAndView.addObject("qualifications",qualifications);
        modelAndView.addObject("poztses",poztses);
        String viewAdapter = adapter.viewAdapter("user/userInfo");
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @RequiresPermissions("usermsg:edit")
    @GetMapping("{id}/toEidtUser.html")
    public ModelAndView toEidtUser(ModelAndView modelAndView, @PathVariable("id") Integer id) {
        SysUser user = userService.getUserById(id);
        List<Qualification>  qualifications = (List<Qualification>)redisService.get(PREFIX+"QUALIFICATIONS");
        modelAndView.addObject("qualifications",qualifications);
        modelAndView.addObject("user",user);
        String viewAdapter = adapter.viewAdapter("user/userInfo");
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @RequiresPermissions("usermsg:search")
    @PostMapping("userList.html")
    public String userList(Integer page,Integer limit,SysUser user){
        PageHelper.startPage(page, limit);
        SysUser loginUser = SysUtil.getLoginUser();
        String username = loginUser.getUsername();
        user.setUpdateuser(username);
        Page<Object> pagehelperPage = PageHelper.getLocalPage();
        List<SysUser> userList = userService.getAllUsers(user);
        String layjson = LayUtil.getLayJsonWeChat(userList,pagehelperPage.getTotal(),page);
        return layjson;

    }

    @RequiresPermissions(value = {"usermsg:add", "usermsg:edit"}, logical = Logical.OR )
    @PostMapping("saveOrUpdateUser.html")
    public String saveOrUpdate(SysUser sysUser){
        Result result = Result.success();
        Integer id = sysUser.getId();
        if (id==null){
            sysUser.setUpdateuser(SysUtil.getLoginUser().getUsername());
            String s = Md5Util.MD5AndSalt((String) redisService.get(PREFIX + "PARAS-DEF-PASSWORD"), sysUser.getUsername());
            sysUser.setPassword(s);
            boolean b = userService.saveOrUpdateUser(sysUser);
            if (!b)
                result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        }else {
            boolean b = userService.saveOrUpdateUser(sysUser);
            if (!b)
                result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        }
        return gson.toJson(result);

    }


}
