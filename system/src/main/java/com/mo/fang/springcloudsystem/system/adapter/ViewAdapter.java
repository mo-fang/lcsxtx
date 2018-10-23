package com.mo.fang.springcloudsystem.system.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.JudgeUtil;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 视图适配器
 * create by Mofang_ysc on 2018/9/1 0001
 */
@Component
public class ViewAdapter {
    @Autowired
    private HttpServletRequest request;
    @Value("${website.view.pc-prefix}")
    private String PCPREFIX;
    @Value("${website.view.mobile-prefix}")
    private String MOBILEPREFIX;
    public String viewAdapter(String viewName){
        boolean isMoblie = JudgeUtil.JudgeIsMoblie(request);
        if (isMoblie)
            viewName=this.PCPREFIX+viewName;
        else
            viewName=this.PCPREFIX+viewName;
        return viewName;
    }


}
