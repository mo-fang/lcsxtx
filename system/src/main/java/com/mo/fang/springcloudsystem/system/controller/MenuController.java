package com.mo.fang.springcloudsystem.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.Button;
import com.mo.fang.springcloudsystem.system.entity.Menu;
import com.mo.fang.springcloudsystem.system.entity.MenuAndButton;
import com.mo.fang.springcloudsystem.system.serviceI.MenuAndButtonService;
import com.mo.fang.springcloudsystem.system.serviceI.MenuService;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/17 0017
 * 系统菜单管理
 */
@RestController
public class MenuController {
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;
    @Autowired
    private ViewAdapter adapter;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuAndButtonService menuAndButtonService;
    @Autowired
    private RedisService redisService;

    private Gson gson = new Gson();

    @RequiresPermissions("menumsg:search")
    @GetMapping("toMenuList.html")
    public ModelAndView toMenuList(ModelAndView modelAndView) {
        List<Menu> menus = (List<Menu>) redisService.get(PREFIX + "MENUS");
        modelAndView.addObject("menus", menus);
        List<Menu> pMenus = new ArrayList<>();
        menus.forEach(menu -> {
            String parentId = menu.getParentMenucode();
            if ("ROOT".equals(parentId)) pMenus.add(menu);
        });
        modelAndView.addObject("pMenus", pMenus);
        modelAndView.setViewName(adapter.viewAdapter("menu/menulist"));
        return modelAndView;
    }

    @RequiresPermissions("menumsg:edit")
    @GetMapping("{id}/toEidtMenu.html")
    public ModelAndView toEidtMenu(ModelAndView modelAndView, @PathVariable("id") Integer id) {
        Menu menuedit = menuService.selectByPrimaryKey(id);
        if (menuedit!=null){
            if("1".equals(menuedit.getMenuType())){
                MenuAndButton menuAndButton = new MenuAndButton();
                menuAndButton.setMenuId(id);
                List<MenuAndButton> allMenuAndButton = menuAndButtonService.getAllMenuAndButton(menuAndButton);
                List<Integer> buttons1 = new ArrayList();
                allMenuAndButton.forEach(menuAndButton1 -> {
                    buttons1.add(menuAndButton1.getButtonId());
                });
                modelAndView.addObject("buttonses",buttons1);
            }
            modelAndView.addObject("menu", menuedit);
            List<Menu> menus = (List<Menu>) redisService.get(PREFIX + "MENUS");
            List<Menu> pMenus = new ArrayList<>();
            menus.forEach(menu -> {
                String parentId = menu.getParentMenucode();
                if ("ROOT".equals(parentId)) pMenus.add(menu);
            });
            modelAndView.addObject("pMenus", pMenus);
            List<Button> buttons = (List<Button>) redisService.get(PREFIX + "BUTTONS");
            modelAndView.addObject("buttons", buttons);
        }
        modelAndView.setViewName(adapter.viewAdapter("menu/edit"));
        return modelAndView;
    }

    @RequiresPermissions("menumsg:add")
    @GetMapping("toAddMenu.html")
    public ModelAndView toAddMneu(ModelAndView modelAndView) {
        List<Menu> menus = (List<Menu>) redisService.get(PREFIX + "MENUS");
        List<Menu> pMenus = new ArrayList<>();
        menus.forEach(menu -> {
            String parentId = menu.getParentMenucode();
            if ("ROOT".equals(parentId)) pMenus.add(menu);
        });
        List<Button> buttons = (List<Button>) redisService.get(PREFIX + "BUTTONS");
        modelAndView.addObject("pMenus", pMenus);
        modelAndView.addObject("buttons", buttons);
        modelAndView.setViewName(adapter.viewAdapter("menu/add"));
        return modelAndView;
    }
    @RequiresPermissions("menumsg:add")
    @PostMapping("doAddMenu.html")
    public String doAddMenu(Menu menu, String[] buttons) {
        Result result = Result.success();
        boolean flag = menuService.saveOrUpdate(menu, buttons);
        if (!flag)
            result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        return gson.toJson(result);
    }
    @RequiresPermissions("menumsg:del")
    @PostMapping("doDelMenu.html")
    public String doDelMenu(Integer id) {
        Result result = Result.success();
        boolean flag = menuService.deleteByPrimaryKey(id);
        if (!flag)
            result = Result.error(CodeMsg.DEL_FAIL);
        return gson.toJson(result);
    }

    @RequiresPermissions("menumsg:search")
    @PostMapping("menuList.html")
    public String menuList(Integer page, Integer limit, Menu menu) {
        PageHelper.startPage(page, limit);
        Page<Object> pagehelperPage = PageHelper.getLocalPage();
        List<Menu> menuList = menuService.getAllMenus(menu);
        String layjson = LayUtil.getLayJsonWeChat(menuList, pagehelperPage.getTotal(), page);
        return layjson;
    }

}
