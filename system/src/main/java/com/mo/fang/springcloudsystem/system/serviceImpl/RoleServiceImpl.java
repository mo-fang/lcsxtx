package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.entity.*;
import com.mo.fang.springcloudsystem.system.mapper.AuthMapper;
import com.mo.fang.springcloudsystem.system.mapper.MenuAndButtonMapper;
import com.mo.fang.springcloudsystem.system.mapper.RoleMapper;
import com.mo.fang.springcloudsystem.system.serviceI.MenuService;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import com.mo.fang.springcloudsystem.system.serviceI.RoleService;
import com.mo.fang.springcloudsystem.system.util.SysUtil;
import entity.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.MapperFlag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/22 0022
 */

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisService redisService;
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;

    private Gson gson = new Gson();


    @Transactional
    @Override
    public boolean saveOrUpdateRole(Role role) {
        SysUser loginUser = SysUtil.getLoginUser();
        String username = loginUser.getUsername();
        role.setInsertusername(username);
        int insert = roleMapper.saveOrUpdateRole(role);
        return MapperFlag.tOrf(insert);
    }

    @Override
    public List<Role> getAllRoles(Role role) {
        return roleMapper.getAllRoles(role);
    }

    @Override
    public String getAllAuth(List<Menu> menuList,List<MenuAndButton>menuAndButtons,Integer id) {
        List<Menu> listMenu = menuService.getMenuist(SysUtil.getLoginUser());

        List<Integer> mbidList = authMapper.getMenuAndButtonIdByAuthTypeAndOwnerId("1", id);
        List<LayTree> rootList = new ArrayList<>();
        listMenu.forEach(menup -> {
            String pmenuType = menup.getMenuType();
            String pmenuCode = menup.getMenuCode();
            LayTree layTreep;
            if ("0".equals(pmenuType)){
                layTreep = new LayTree();
                layTreep.setTitle(menup.getMenuName());
                layTreep.setValue(menup.getId());
                List<LayTree> layTreeLists = new ArrayList<>();
                listMenu.forEach(menus->{
                    LayTree layTrees;
                    String parentMenucode = menus.getParentMenucode();
                    if (pmenuCode.equals(parentMenucode)){
                        layTrees = new LayTree();
                        Integer idmen = menus.getId();
                        layTrees.setTitle(menus.getMenuName());
                        layTrees.setValue(idmen);
                        List<LayTree> layTreeListBtn = new ArrayList<>();
                        menuAndButtons.forEach(menuAndButton -> {
                            LayTree layTreebtn;
                            Integer menuId = menuAndButton.getMenuId();
                            if (idmen == menuId){
                                layTreebtn = new LayTree();
                                Integer idmandb = menuAndButton.getId();
                                layTreebtn.setValue(idmandb);
                                layTreebtn.setChecked(mbidList.contains(idmandb)?true:false);
                                layTreebtn.setTitle(menuAndButton.getBtnName());
                                layTreebtn.setData(new ArrayList<>());
                                layTreeListBtn.add(layTreebtn);
                            }
                        });
                        layTrees.setData(layTreeListBtn);
                        layTreeLists.add(layTrees);
                    }
                });
                layTreep.setData(layTreeLists);
                rootList.add(layTreep);
            }
        });

        return gson.toJson(rootList);
    }
    @Transactional
    @Override
    public boolean doAuthRole(Integer id, Integer[] mbid) {
        SysUser loginUser = SysUtil.getLoginUser();
        String username = loginUser.getUsername();
        boolean flag = true;
        if (mbid==null){
            int i = authMapper.deleteByTypeAndOwnerId("1", id);
            if (i<0)
                flag = false;
        }else {
            int i = authMapper.deleteByTypeAndOwnerId("1", id);
            if (i<0){
                flag = false;
                throw new RuntimeException();
            }else {
                List<Auth> list = new ArrayList<>();
                List<Integer> mbidlist = Arrays.asList(mbid);
                mbidlist.forEach(mandbid->{
                    Auth auth = new Auth();
                    auth.setMbid(mandbid);
                    auth.setOwnerid(id);
                    auth.setType("1");
                    auth.setUpdateuser(username);
                    list.add(auth);
                });
                int i1 = authMapper.insertBatch(list);
                if (i1<0){
                    flag = false;
                    throw new RuntimeException();
                }
            }

        }

        return flag;
    }

    @Transactional
    @Override
    public boolean doDelRole(Integer id) {
//        删除该角色下的权限信息及该角色下的用户信息
        int i1 = roleMapper.delAuthAndUandRole(id);
        int i2 = roleMapper.delAuthByOwnerId(id);
        int i = roleMapper.deleteByPrimaryKey(id);
        boolean flag = (i<0||i1<0||i2<0)?false:true;
        if (!flag)
            throw  new RuntimeException(CodeMsg.SERVER_EXCEPTION.toString());
        else
            SysUtil.clearAuth();
        return flag;
    }

    @Override
    public Role selectByPrimaryKey(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }


    @Transactional
    @Override
    public boolean addUandR(Integer id, Integer roleid) {
        int i1 = roleMapper.deleteuAndRByuserId(id);
        boolean flag = i1<0?false:true;
        if (!flag)
            throw  new RuntimeException(CodeMsg.CHANGE_ROLE.toString());
        int i = roleMapper.addUandR(id, roleid);
         flag = i<0?false:true;
        if (!flag)
            throw  new RuntimeException(CodeMsg.CHANGE_ROLE.toString());
        return flag;
    }
}
