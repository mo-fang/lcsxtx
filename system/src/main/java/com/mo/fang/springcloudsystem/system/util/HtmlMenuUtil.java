package com.mo.fang.springcloudsystem.system.util;

import com.google.gson.Gson;
import com.mo.fang.springcloudsystem.system.entity.Menu;

import java.util.List;

/**
 * create by Mofang_ysc on 2018/9/18 0018
 */

public class HtmlMenuUtil {

    private String title;
    private String icon;
    private String href;
    private boolean spread;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public HtmlMenuUtil(String title, String icon, String href, boolean spread) {
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }

    //       <li class="layui-nav-item">
    //                    <a class="" href="javascript:;">系统管理</a>
//                    <dl class="layui-nav-child">
//                        <dd>
//                            <a href="javascript:;" data-id="1" data-title="菜单管理" data-url="toMenuList.html" class="site-demo-active" data-type="tabAdd">菜单管理</a></dd>
//                    </dl>
//                </li>
    public final static String li = "<li class='layui-nav-item'>";
    public final static  String li01 = "<a class='' href='javascript:;'>";
    private String  pMenuName;
    public final static  String li02 = "</a>";
    public final static String li03 = "<dl class='layui-nav-child'>";
    public final static String li04 = "<dd><a href='javascript:;' data-id='";
    private int id;
    public final static String li05 = "'  data-title='";
    private String title1;
    public final static String li06 = "' data-url='";
    private String utl;
    public final static  String li07 = "' class='site-demo-active' data-type='tabAdd'>";
    private String sMenuName;
    public final static String li08 = "</a></dd>";
    public final static String li09 = "</dl></li>";

    public String getpMenuName() {
        return pMenuName;
    }

    public void setpMenuName(String pMenuName) {
        this.pMenuName = pMenuName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUtl() {
        return utl;
    }

    public void setUtl(String utl) {
        this.utl = utl;
    }

    public String getsMenuName() {
        return sMenuName;
    }

    public void setsMenuName(String sMenuName) {
        this.sMenuName = sMenuName;
    }

   String getMenuList(String pMenuName, List<Menu> menus){

        return  new Gson().toJson("");
   }
}
