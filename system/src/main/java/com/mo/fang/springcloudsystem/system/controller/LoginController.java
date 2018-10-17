package com.mo.fang.springcloudsystem.system.controller;

import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import entity.CodeMsg;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import utils.JsonUtil;
import utils.Result;
import com.mo.fang.springcloudsystem.system.util.SysUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * create by Mofang_ysc on 2018/9/3 0003
 */


@RestController
public class LoginController {
    @Autowired
    private ViewAdapter adapter;

    @GetMapping("login.html")
    public ModelAndView login(HttpServletRequest request, ModelAndView modelAndView) {
        String viewName = adapter.viewAdapter("login");
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @PostMapping("doLogin.html")
    public String doLogin(SysUser user) {
        Result result = Result.success();
        Subject currentUser = SecurityUtils.getSubject(); //获取当前的对象
        //判断是不是之前认证过
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
//            token.setRememberMe(true);
            try {
                currentUser.login(token);
                SysUser loginUser = SysUtil.getLoginUser();
                result.success(loginUser);
            } catch (AuthenticationException e) {
                result = result.error(CodeMsg.USER_NAMEORPASSWORD_ERROR);
                e.printStackTrace();
            }
        }
        return JsonUtil.getInstance().toJson(result);
    }

}
