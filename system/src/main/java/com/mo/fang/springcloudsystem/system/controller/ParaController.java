package com.mo.fang.springcloudsystem.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.Role;
import com.mo.fang.springcloudsystem.system.entity.SysPara;
import com.mo.fang.springcloudsystem.system.serviceI.ParaService;
import com.mo.fang.springcloudsystem.system.util.SysUtil;
import entity.CodeMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.LayUtil;
import utils.Result;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/28 0028
 */
@RestController
public class ParaController {
    @Autowired
    private ViewAdapter adapter;
    @Autowired
    private ParaService paraService;

    private Gson gson = new Gson();
    @RequiresPermissions("paramsg:search")
    @GetMapping("toParaList.html")
    public ModelAndView toParaList(ModelAndView modelAndView){
        String viewAdapter = adapter.viewAdapter("para/paralist");
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @RequiresPermissions("paramsg:add")
    @GetMapping("toAddPara.html")
    public ModelAndView toAddPara(ModelAndView modelAndView){
        String viewAdapter = adapter.viewAdapter("para/add");
        modelAndView.setViewName(viewAdapter);
        return modelAndView;
    }
    @RequiresPermissions("menumsg:edit")
    @GetMapping("{id}/toEidtPara.html")
    public ModelAndView toEidtMenu(ModelAndView modelAndView, @PathVariable("id") Integer id) {
        SysPara sysPara = paraService.selectByPrimaryKey(id);
        modelAndView.addObject("para",sysPara);
        modelAndView.setViewName(adapter.viewAdapter("para/edit"));
        return modelAndView;
    }

    @RequiresPermissions(value = {"paramsg:add","paramsg:edit"})
    @PostMapping("doSaveOrUpdatePara.html")
    public String doSaveOrUpdatePara(ModelAndView modelAndView,SysPara sysPara){
        Result result = Result.success();
        Integer id = sysPara.getId();
        if (id==null){
            sysPara.setInsertuser(SysUtil.getLoginUser().getUsername());
            boolean flag = paraService.saveOrUpdate(sysPara);
            if (!flag)
                result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        }else{
            boolean flag = paraService.saveOrUpdate(sysPara);
            if (!flag)
                result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        }
        return gson.toJson(result);
    }
    @RequiresPermissions("paramsg:del")
    @PostMapping("doDelPara.html")
    public String doDelPara(Integer id) {
        Result result = Result.success();
        boolean flag = paraService.delParaById(id);
        if (!flag)
            result = Result.error(CodeMsg.DEL_FAIL);
        return gson.toJson(result);
    }
    @RequiresPermissions("paramsg:search")
    @PostMapping("paraList.html")
    public String paraList(Integer page, Integer limit, SysPara sysPara) {
        PageHelper.startPage(page, limit);
        Page<Object> pagehelperPage = PageHelper.getLocalPage();
        List<SysPara> sysParas = paraService.getAllParas(sysPara);
        String layjson = LayUtil.getLayJsonWeChat(sysParas, pagehelperPage.getTotal(), page);
        return layjson;

    }

}
