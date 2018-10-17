package com.mo.fang.springcloudsystem.system.util;

import com.mo.fang.springcloudsystem.system.configs.AuthRealm;
import com.mo.fang.springcloudsystem.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;

import java.util.Properties;

public class SysUtil {
    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }
    public  static SysUser getLoginUser(){
        SysUser sysUser_isAuthenticated = (SysUser)SecurityUtils.getSubject().getPrincipal();//获取已经认证过的用户
        return sysUser_isAuthenticated;
    }
    public static void clearAuth(){
        RealmSecurityManager realmSecurityManager = (RealmSecurityManager)SecurityUtils.getSecurityManager();
        AuthRealm authRealm = (AuthRealm)realmSecurityManager.getRealms().iterator().next();
        authRealm.removeUserAuthorizationInfoCache();
    }
}
