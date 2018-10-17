package com.mo.fang.springcloudsystem.system.handler;

import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import com.mo.fang.springcloudsystem.system.util.SysUtil;
import entity.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 2018年9月26日11:18:06
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private ViewAdapter adapter;

    public static final String DEFAUL_ERROR_VIEW = "error";

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest req, Exception e) {
        SysUser loginUser = SysUtil.getLoginUser();
        e.printStackTrace();
        String message = e.getMessage();
        if(isAjax(req)){
            Result result = Result.error(CodeMsg.SERVER_EXCEPTION);
            return  new Gson().toJson(result);//返回异常结果类
        }else{
            String viewAdapter = adapter.viewAdapter(DEFAUL_ERROR_VIEW);
            ModelAndView mav = new ModelAndView();
            mav.addObject("name",loginUser.getName());
            mav.addObject("Exception",e);
            mav.addObject("url",req.getRequestURL());
            mav.addObject("msg",message);
            mav.setViewName(viewAdapter);
            return mav;
        }
    }

    /**
     * 是否是ajax 请求
     * @param req
     * @return
     */
    public static Boolean isAjax(HttpServletRequest req){
        return req.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(req.getHeader("X-Requested-With").toString());
    }

}


