package com.mo.fang.springcloudsystem.system.runner;

import com.mo.fang.springcloudsystem.system.entity.*;
import com.mo.fang.springcloudsystem.system.serviceI.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ButtonService buttonService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuAndButtonService menuAndButtonService;
    @Autowired
    private ParaService paraService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private QualificationService qualificationService;
    @Autowired
    private PoztsService poztsService;
    @Autowired
    private RoleService roleService;

    Logger logger = Logger.getLogger(MyCommandLineRunner.class);

    @Override
    public void run(String... strings) throws Exception {
        logger.info("System parameters are being loaded");
        redisService.redisCleardb();
//        系统按钮
        List<Button> buttonList = buttonService.getAllButtons();
        buttonList.forEach(button -> {
            redisService.set(PREFIX+"BUTTONS"+button.getId(),button);
        });
        redisService.set(PREFIX+"BUTTONS",buttonList);
//        菜单
        List<Menu> menuList = menuService.getAllMenus(new Menu());
        redisService.set(PREFIX+"MENUS",menuList);
//        菜单按钮
        List<MenuAndButton>  menuAndButtons = menuAndButtonService.getAllMenuAndButton(new MenuAndButton());
        redisService.set(PREFIX+"MENUANDBUTTONS",menuAndButtons);
//       系统参数
        List<SysPara> sysParas = paraService.getAllParas(new SysPara());
        sysParas.forEach(para->{
            redisService.set(PREFIX+"PARAS-"+para.getName(),para.getValue());
        });
//      部门
        List<Department> departments = departmentService.getDeparts(new Department());
        redisService.set(PREFIX+"DEPARTMENTS",departments);
//       职能
        List<Pozts> poztses = poztsService.getPoztses(new Pozts());
        redisService.set(PREFIX+"POZTSES",poztses);
//        学历
        List<Qualification> qualifications = qualificationService.getQualifications(new Qualification());
        redisService.set(PREFIX+"QUALIFICATIONS",qualifications);
//        ROLE 角色
        List<Role> allRoles = roleService.getAllRoles(new Role());
        redisService.set(PREFIX+"ROLES",allRoles);
        logger.info("System parameters were loaded");

    }
}
