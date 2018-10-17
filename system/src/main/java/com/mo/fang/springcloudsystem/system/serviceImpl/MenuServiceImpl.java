package com.mo.fang.springcloudsystem.system.serviceImpl;

import com.mo.fang.springcloudsystem.system.entity.Button;
import com.mo.fang.springcloudsystem.system.entity.Menu;
import com.mo.fang.springcloudsystem.system.entity.MenuAndButton;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import com.mo.fang.springcloudsystem.system.event.LoadingDataEvent;
import com.mo.fang.springcloudsystem.system.mapper.MenuAndButtonMapper;
import com.mo.fang.springcloudsystem.system.mapper.MenuMapper;
import com.mo.fang.springcloudsystem.system.serviceI.MenuService;
import com.mo.fang.springcloudsystem.system.serviceI.RedisService;
import com.mo.fang.springcloudsystem.system.util.HtmlMenuUtil;
import org.apache.commons.collections.set.ListOrderedSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.JsonUtil;

import javax.xml.bind.util.JAXBSource;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 菜单
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuAndButtonMapper menuAndButtonMapper;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private RedisService redisService;
    @Value("${oa.redis.system-data.prefix}")
    private String PREFIX;
    @Override
    public List<Menu> getAllMenus(Menu menu) {
        return menuMapper.getAllMenus(menu);
    }

    @Override
    public boolean saveOrUpdate(Menu menu,String[] buttons) {
        boolean flag = true;
        Integer id1 = menu.getId();
        List<Button> buttonList = new ArrayList<>();
        if (id1==null){
            menu.setMenuClass("#");
//            新增
            if (buttons!=null&&buttons.length>0){
                List<String> strings = Arrays.asList(buttons);
                strings.forEach(id->{
                    buttonList.add((Button) redisService.get(PREFIX+"BUTTONS"+id));
                });
            }
            if("0".equals(menu.getMenuType())){
                menu.setMenuUrl("#");
                menu.setParentMenucode("ROOT");
            };
            int i = menuMapper.saveOrUpdate(menu);
            flag = i>0?true:false;
            //当menu插入成功只有 button才继续插入  其实这样也不严谨   当button插入失败的时候呢？  留坑！（级联回滚）
            if(flag){
                List<MenuAndButton> menuAndButtons = new ArrayList<>();
                Integer id = menu.getId();
                String menuCode = menu.getMenuCode();
                String menuName = menu.getMenuName();
                if (buttonList!=null && buttonList.size()>0){
                    buttonList.forEach(button -> {
                        menuAndButtons.add(new MenuAndButton(id,button.getId(),menuCode,button.getShortname(),button.getButtonName(),menuName));
                    });
                    int ib = menuAndButtonMapper.insertBatch(menuAndButtons);
                    flag = ib>0?true:false;
                }
            }
        }else{
//          修改
            if("0".equals(menu.getMenuType())){
                menu.setMenuUrl("#");
                menu.setParentMenucode("ROOT");
            };
            int j = menuMapper.saveOrUpdate(menu);
            flag = j>0?true:false;
            if (flag&&buttons==null){
                int i = menuAndButtonMapper.deleteByMenuId(id1);
                flag = i<0?false:true;
            }else if(flag){
                List<String> strings = Arrays.asList(buttons);
                MenuAndButton menuAndButton = new MenuAndButton();
                menuAndButton.setMenuId(id1);
                List<MenuAndButton> allMenuAndButton = menuAndButtonMapper.getAllMenuAndButton(menuAndButton);
                List<String> insertList = new ArrayList<>();
                List<String> delList = new ArrayList<>();
                allMenuAndButton.forEach(menuAndButton1 -> {
                    Integer buttonId = menuAndButton1.getButtonId();
                    boolean flagdel = strings.contains(buttonId.toString());
                    if (!flagdel){
                        delList.add(buttonId.toString());
                    }
                });
                strings.forEach(id->{
                    List list = new ArrayList();
                    for(MenuAndButton menuAndButton1:allMenuAndButton){
                        Integer buttonId = menuAndButton1.getButtonId();
                        list.add(buttonId.toString());
                    }

                    boolean flaginsert = list.contains(id);
                    if(!flaginsert){
                        insertList.add(id);
                    }
                });


                if (delList.size()!=0){
                    int i = menuAndButtonMapper.delByMenuIdAndButtonId(id1, delList);
                    flag = i<0?false:true;
                }

                if (insertList.size()!=0){
                    insertList.forEach(id->{
                        buttonList.add((Button) redisService.get(PREFIX+"BUTTONS"+id));
                    });
                    List<MenuAndButton> menuAndButtons = new ArrayList<>();
                    Integer id = menu.getId();
                    String menuName = menu.getMenuName();
                    String menuCode = menu.getMenuCode();
                    buttonList.forEach(button -> {
                            menuAndButtons.add(new MenuAndButton(id1,button.getId(),menuCode,button.getShortname(),button.getButtonName(),menuName));
                        });
                        int ib = menuAndButtonMapper.insertBatch(menuAndButtons);
                        if(flag)
                            flag = ib>0?true:false;
                }
            }
        }

        if(flag){
            applicationContext.publishEvent(new LoadingDataEvent(this,menuMapper));
            applicationContext.publishEvent(new LoadingDataEvent(this,menuAndButtonMapper));
        }
        return flag ;
    }

    @Override
    public List<Menu> getMenuByUser(SysUser user) {
        return menuMapper.getMenuByUser(user);
    }

    @Override
    public List<Menu> getMenuist(SysUser user) {
        List<Menu> menusAll = (List<Menu>)redisService.get(PREFIX+"MENUS");
        List<Menu> allMenus = new ArrayList<>();

        menusAll.forEach(menu -> {
            String parentMenucode = menu.getParentMenucode();
            if("ROOT".equals(parentMenucode))
                allMenus.add(menu);
        });
        List<Menu> menuByUser = menuMapper.getMenuByUser(user);//得到该用户下所有的菜单名称
        AtomicBoolean flag = new AtomicBoolean(false);
        List<Menu> sMenus = new ArrayList<Menu>();
        AtomicInteger id = new AtomicInteger();
        StringBuffer resultBuffer = new StringBuffer("");
        allMenus.forEach(pMenu->{
            String menuCode = pMenu.getMenuCode();
            menuByUser.forEach(smenu->{
                String parentMenucode = smenu.getParentMenucode();
                if(parentMenucode.equals(menuCode)){
                    flag.set(true);
                    sMenus.add(smenu);
                }
            });
            if (flag.get()){
                menuByUser.add(pMenu);
//                resultBuffer.append(HtmlMenuUtil.li);
//                flag.set(false);
//                resultBuffer.append(HtmlMenuUtil.li01).append(pMenu.getMenuName()).append(HtmlMenuUtil.li02).append(HtmlMenuUtil.li03);
//                sMenus.forEach(sMenu->{
//                    resultBuffer.append(HtmlMenuUtil.li04).append(id.getAndIncrement()).
//                            append(HtmlMenuUtil.li05).append(sMenu.getMenuName()).append(HtmlMenuUtil.li06).append(sMenu.getMenuUrl()).
//                            append(HtmlMenuUtil.li07).append(sMenu.getMenuName()).append(HtmlMenuUtil.li08);
//                });
//                resultBuffer.append(HtmlMenuUtil.li09);
            }
            sMenus.clear();
        });
        return menuByUser;
    }

    @Override
    public String getMenuJson(SysUser user) {
        List<Menu> menusAll = (List<Menu>)redisService.get(PREFIX+"MENUS");
        List<Menu> allMenus = new ArrayList<>();

        menusAll.forEach(menu -> {
            String parentMenucode = menu.getParentMenucode();
            if("ROOT".equals(parentMenucode))
                allMenus.add(menu);
        });
        List<Menu> menuByUser = menuMapper.getMenuByUser(user);//得到该用户下所有的菜单名称
        AtomicBoolean flag = new AtomicBoolean(false);
        List<Menu> sMenus = new ArrayList<Menu>();
        AtomicInteger id = new AtomicInteger();
        Map<String,List<HtmlMenuUtil>> menuMap = new HashMap<>();
        StringBuffer resultBuffer = new StringBuffer("");
        allMenus.forEach(pMenu->{
            String menuCode = pMenu.getMenuCode();
            menuByUser.forEach(smenu->{
                String parentMenucode = smenu.getParentMenucode();
                if(parentMenucode.equals(menuCode)){
                    flag.set(true);
                    sMenus.add(smenu);
                }
            });
            if (flag.get()){
                List a  = new ArrayList();
                for(Menu menu:sMenus){
                    HtmlMenuUtil htmlMenuUtil = new HtmlMenuUtil(menu.getMenuName(), menu.getIcon(), menu.getMenuUrl(), false);
                    a.add(htmlMenuUtil);
                }
                menuMap.put(pMenu.getMenuCode(),a);
            }
            sMenus.clear();
        });
        return JsonUtil.getInstance().toJson(menuMap);
    }

    @Override
    @Transactional
    public boolean deleteByPrimaryKey(Integer id) {
        int i = menuMapper.deleteByPrimaryKey(id);
        int i1 = menuAndButtonMapper.deleteByMenuId(id);
        boolean flag =  i>0?true:false;
         flag =  i1>0?true:false;
        if (flag){
            applicationContext.publishEvent(new LoadingDataEvent(this,menuMapper));
            applicationContext.publishEvent(new LoadingDataEvent(this,menuAndButtonMapper));
        }
        return flag;
    }

    @Override
    public Menu selectByPrimaryKey(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }


}
