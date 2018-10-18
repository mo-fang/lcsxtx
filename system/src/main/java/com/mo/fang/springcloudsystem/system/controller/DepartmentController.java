package com.mo.fang.springcloudsystem.system.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mo.fang.springcloudsystem.system.adapter.ViewAdapter;
import com.mo.fang.springcloudsystem.system.entity.Department;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import com.mo.fang.springcloudsystem.system.serviceI.DepartmentService;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import com.mo.fang.springcloudsystem.system.serviceI.UserService;
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
import utils.JsonUtil;
import utils.LayUtil;
import utils.Result;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/30 0030
 */

@RestController
public class DepartmentController {

    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private ViewAdapter adapter;

    @RequiresPermissions("depmsg:search")
    @GetMapping("toDepartment.html")
    public ModelAndView toDepartment(ModelAndView modelAndView){
        modelAndView.setViewName(adapter.viewAdapter("department/departmentlist"));
        return modelAndView;
    }
    @RequiresPermissions("depmsg:add")
    @GetMapping("toAddDepartment.html")
    public ModelAndView toAddDepartment(ModelAndView modelAndView){
        List<Department> departments = (List<Department>)redisService.get(PREFIX+"DEPARTMENTS");
        modelAndView.addObject("departments",departments);
        modelAndView.setViewName(adapter.viewAdapter("department/add"));
        return modelAndView;
    }

    @RequiresPermissions("depmsg:edit")
    @GetMapping("{id}/toEidtDepartment.html")
    public ModelAndView toEidtMenu(ModelAndView modelAndView, @PathVariable("id") Integer id) {
        Department department = departmentService.selectByPrimaryKey(id);
        modelAndView.addObject("department",department);
        modelAndView.setViewName(adapter.viewAdapter("department/edit"));
        return modelAndView;
    }

    @RequiresPermissions("depmsg:add")
    @PostMapping("saveOrUpdateAddDepartment.html")
    public String saveOrUpdateAddDepartment(Department department){
        Result result = Result.success();
        SysUser loginUser = SysUtil.getLoginUser();
        if(department.getId()==null)
            department.setCreatename(loginUser.getUsername());
        boolean b = departmentService.saveOrUpdateDepartment(department);
        if(!b)
            result = Result.error(CodeMsg.SAVE_OR_UPDATE_FAIL);
        return JsonUtil.getInstance().toJson(result);

    }
    @RequiresPermissions("depmsg:del")
    @PostMapping("doDelDepartment.html")
    public String doDelDepartment(Integer id){
        Result result = Result.success();
        SysUser user = new SysUser();
        user.setDepartmentid(id);
        List<SysUser> allUsers = userService.getAllUsers(user);
        if(allUsers!=null&& allUsers.size()>0)
            result = Result.error(CodeMsg.HAVE_RELATE_RECORD);
        else{
            boolean b = departmentService.deleteByPrimaryKey(id);
            if(!b){
                result = Result.error(CodeMsg.DEL_FAIL);
            }
        }

        return JsonUtil.getInstance().toJson(result);
    }
    @RequiresPermissions("depmsg:search")
    @PostMapping("departmentList.html")
    public String categoryList(Integer page,Integer limit,Department department){
        PageHelper.startPage(page, limit);
        Page<Object> pagehelperPage = PageHelper.getLocalPage();
        List<Department> departments = departmentService.getDeparts(department);
        String layjson = LayUtil.getLayJsonWeChat(departments,pagehelperPage.getTotal(),page);
        return layjson;

    }

}
